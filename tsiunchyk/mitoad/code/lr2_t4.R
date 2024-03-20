library(dplyr)
flights <- readRDS("Data/flights.RData")

# Функция для преобразования времени из формата HHMM в HH:MM
format_time <- function(time) {
  hours <- time %/% 100
  minutes <- time %% 100
  sprintf("%02d:%02d", hours, minutes)
}

# Преобразование временных столбцов
flights_transformed <- flights %>%
  mutate(
    dep_time_formatted = format_time(dep_time),
    sched_dep_time_formatted = format_time(sched_dep_time),
    arr_time_formatted = format_time(arr_time),
    sched_arr_time_formatted = format_time(sched_arr_time),
    air_time_formatted = format_time(air_time)
  )

# Печать преобразованных временных столбцов
print(select(flights_transformed, dep_time_formatted, sched_dep_time_formatted, arr_time_formatted, sched_arr_time_formatted, air_time_formatted))

# Сравнение air_time и arr_time - dep_time
flights_comparison <- flights_transformed %>%
  mutate(
    air_time_minutes = air_time,
    time_diff = (arr_time %% 100 + (arr_time %/% 100) * 60) - (dep_time %% 100 + (dep_time %/% 100) * 60),
    comparison = air_time_minutes - time_diff
  )

# Печать сравнения между air_time и разницей arr_time - dep_time
print(select(flights_comparison, air_time_minutes, time_diff, comparison))

# Сравнение dep_time, sched_dep_time и dep_delay
flights_delay_comparison <- flights_transformed %>%
  mutate(
    dep_delay_minutes = dep_delay,
    scheduled_dep_time_minutes = (sched_dep_time %/% 100) * 60 + (sched_dep_time %% 100),
    actual_dep_time_minutes = (dep_time %/% 100) * 60 + (dep_time %% 100)
  ) %>%
  arrange(desc(dep_delay_minutes))

# Печать сравнения между dep_time, sched_dep_time и dep_delay
print(select(flights_delay_comparison, scheduled_dep_time_minutes, actual_dep_time_minutes, dep_delay_minutes))

# Нахождение 10 рейсов с наибольшей задержкой
top_delays <- flights_delay_comparison %>%
  mutate(rank = min_rank(desc(dep_delay_minutes))) %>%
  filter(rank <= 10)

# Печать 10 рейсов с наибольшей задержкой
print(top_delays)
