library(dplyr)
flights <- readRDS("Data/flights.RData")

# Сортировка по переменной с NA вначале
flights_sorted_by_na <- arrange(flights, desc(is.na(dep_delay)), dep_delay)

# Сортировка по времени задержки прибытия (сначала самые большие задержки)
flights_sorted_by_arrival_delay <- arrange(flights, desc(arr_delay))

# Сортировка по времени задержки отправления (сначала рейсы, отправившиеся раньше времени)
flights_sorted_by_early_departure <- arrange(flights, dep_delay)

# Сортировка по продолжительности полета (сначала самые короткие полеты)
flights_sorted_by_air_time <- arrange(flights, air_time)

print("Сортировка по переменной с NA вначале")
print(flights_sorted_by_na)

print("Сортировка по времени задержки прибытия (сначала самые большие задержки)")
print(flights_sorted_by_arrival_delay)

print("Сортировка по времени задержки отправления (сначала рейсы, отправившиеся раньше времени)")
print(flights_sorted_by_early_departure)

print("Сортировка по продолжительности полета (сначала самые короткие полеты)")
print(flights_sorted_by_air_time)
