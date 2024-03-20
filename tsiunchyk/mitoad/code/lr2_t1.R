library(dplyr)
flights <- readRDS("Data/flights.RData")

# Критерий 1: Время задержки прибытия равно или превышает два часа
flights_with_arrival_delay <- filter(flights, arr_delay >= 120)

# Критерий 2: Отправлялись в Houston (IAH или HOU)
flights_to_houston <- filter(flights, dest %in% c("IAH", "HOU"))

# Критерий 3: Находятся в ведении операторов United Airlines, American Airlines, или Delta Airlines
flights_by_specific_carriers <- filter(flights, carrier %in% c("UA", "AA", "DL"))

# Критерий 4: Отправлялись летом (июнь = 6, июль = 7, август = 8)
flights_in_summer <- filter(flights, month %in% c(6, 7, 8))

# Критерий 5: Прибыли позже более чем на 2 часа, но отправлялись вовремя (dep_delay <= 0)
flights_late_arrival_on_time_departure <- filter(flights, arr_delay > 120, dep_delay <= 0)

# Критерий 6: Задержались, по крайней мере, на час, но наверстали более 30 минут в течение полета
flights_delayed_but_caught_up <- filter(flights, dep_delay >= 60, (arr_delay - dep_delay) < -30)

# Критерий 7: Отправлялись между полночью и 6 часами утра (включительно)
flights_early_morning <- filter(flights, between(dep_time, 0, 600))

# Количество рейсов без указанного dep_time
num_missing_dep_time <- sum(is.na(flights$dep_time))

# Подсчет отсутствующих значений для всех переменных
missing_values_per_column <- colSums(is.na(flights))

# Вывод результатов
print("Рейсы с задержкой прибытия >= 2 часов:")
print(flights_with_arrival_delay)

print("Рейсы в Houston:")
print(flights_to_houston)

print("Рейсы определенных авиакомпаний:")
print(flights_by_specific_carriers)

print("Летние рейсы:")
print(flights_in_summer)

print("Рейсы с поздним прибытием, но своевременным вылетом:")
print(flights_late_arrival_on_time_departure)

print("Рейсы, которые задержались, но наверстали время:")
print(flights_delayed_but_caught_up)

print("Утренние рейсы:")
print(flights_early_morning)

print(paste("Количество рейсов без указанного dep_time:", num_missing_dep_time))

print("Отсутствующие значения по переменным:")
print(missing_values_per_column)
