# Загрузка необходимых библиотек
library(dplyr)
library(ggplot2)

# Загрузка данных о рейсах
flights <- readRDS("Data/flights.RData")

# Создаем функцию для расчета средней задержки с учетом условий
calculate_delay <- function(flights) {
  set.seed(123) # Установка seed для воспроизводимости результатов
  # Симулируем задержку вылета: -15 минут в 50% случаев, иначе 0 минут
  flights$simulated_dep_delay <- ifelse(runif(nrow(flights)) < 0.5, -15, 0)
  # Симулируем задержку прилета: 15 минут в 50% случаев, иначе 10 минут
  flights$simulated_arr_delay <- ifelse(runif(nrow(flights)) < 0.5, 15, 10)
  # В 1% случаев устанавливаем задержку прилета в 2 часа (120 минут)
  flights$simulated_arr_delay <- ifelse(runif(nrow(flights)) < 0.01, 120, flights$simulated_arr_delay)
  
  # Вывод промежуточных данных
  print(head(flights[, c("simulated_dep_delay", "simulated_arr_delay")]))
  
  # Расчет средних значений задержек
  flights %>% summarise(
    avg_dep_delay = mean(simulated_dep_delay, na.rm = TRUE),
    avg_arr_delay = mean(simulated_arr_delay, na.rm = TRUE)
  )
}

# Вызываем функцию и выводим результат
delay_characteristics <- calculate_delay(flights)
print(delay_characteristics) # Вывод средних характеристик задержек

# Определение авиакомпании с наибольшей средней задержкой
carrier_delays <- flights %>%
  group_by(carrier) %>%
  summarise(
    average_dep_delay = mean(dep_delay, na.rm = TRUE),
    average_arr_delay = mean(arr_delay, na.rm = TRUE)
  ) %>%
  arrange(desc(average_dep_delay))

print(carrier_delays) # Вывод авиакомпаний с сортировкой по задержке вылета

# Расчет количества рейсов до первой задержки более чем на час
flights_until_long_delay <- flights %>%
  group_by(tailnum) %>%
  arrange(year, month, day) %>%
  mutate(long_delay = dep_delay > 60) %>%
  filter(cumany(long_delay) == TRUE) %>% 
  summarise(flights_before_long_delay = which(long_delay)[1] - 1)

print(flights_until_long_delay) # Вывод количества рейсов до первой задержки более чем на час

# Построение графика зависимости задержек от расстояния (предполагая, что в flights есть столбцы distance и dep_delay, и переменная count отражает количество рейсов)
#ggplot(data = flights, mapping = aes(x = distance, y = dep_delay)) +
#  geom_point(aes(size = count), alpha = 1 / 3) +
#  geom_smooth(se = FALSE)