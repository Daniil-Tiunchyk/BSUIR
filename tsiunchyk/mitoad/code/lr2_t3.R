# Загрузка необходимого пакета
library(dplyr)
flights <- readRDS("Data/flights.RData")

# Выбор переменных по одному
selected_flights1 <- select(flights, dep_time, dep_delay, arr_time, arr_delay)
print(selected_flights1)

# Выбор переменных с помощью функции contains()
selected_flights2 <- select(flights, contains("dep_time"), contains("dep_delay"), contains("arr_time"), contains("arr_delay"))
print(selected_flights2)

# Выбор переменных с помощью функции starts_with()
selected_flights3 <- select(flights, starts_with("dep"), starts_with("arr"))
print(selected_flights3)

# Выбор переменных с помощью функции matches()
selected_flights4 <- select(flights, matches("^(dep|arr)_(time|delay)$"))
print(selected_flights4)

# Используем оператор ':' для выбора диапазона переменных (порядок столбцов)
selected_flights5 <- select(flights, dep_time:arr_delay)
print(selected_flights5)

# Создаем вектор с именами переменных
vars <- c("year", "month", "day", "dep_delay", "arr_delay")

# Выбор переменных с помощью функции one_of()
selected_flights6 <- select(flights, one_of(vars))
print(selected_flights6)
