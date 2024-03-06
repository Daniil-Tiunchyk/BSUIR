# Загрузка данных из файла
data <- read.csv("Birds.csv")

# Добавление колонок и заполнение значений
data$individualsPerObservation <- data$individualsNumber / data$observations
data$speciesShare <- data$individualsNumber / sum(data$individualsNumber)

# Сортировка данных по убыванию individualsNumber
sorted_data <- data[order(-data$individualsNumber), ]

# Выводим первые строки данных в консоль для проверки
print(head(sorted_data))

# Сохранение модифицированной таблицы в файл
write.csv(sorted_data, "Birds.csv_updated.csv", row.names = FALSE)

# Получение необходимых данных
total_observed_birds <- sum(data$individualsNumber)
median_observations <- median(data$observations)
bird_counts_ranges <- table(cut(data$individualsNumber, breaks = c(0, 10, 50, 100, 500, 1000, Inf)))

max_individuals_bird <- data[which.max(data$individualsNumber), ]$speciesName

# Выводим значения переменных в консоль для проверки
print(total_observed_birds)
print(median_observations)
print(bird_counts_ranges)
print(max_individuals_bird)

# Дополнительные метрики (insights)
# Пример 1: Максимальное количество наблюдений для одной птицы
max_observations <- max(data$observations)

# Пример 2: Среднее количество особей в одном наблюдении
mean_individuals_per_observation <- mean(data$individualsPerObservation)

# Вывод значений дополнительных метрик в консоль
print(max_observations)
print(mean_individuals_per_observation)

# Сохранение результатов в файл
cat(paste("Общее количество наблюдаемых птиц: ", total_observed_birds), "\n", file = "Birds_analysis.txt")
cat(paste("Медианное значение числа наблюдений: ", median_observations), "\n", file = "Birds_analysis.txt")
cat("Перечни названий птиц в различных диапазонах:\n", file = "Birds_analysis.txt")
cat(paste(names(bird_counts_ranges), bird_counts_ranges), "\n", file = "Birds_analysis.txt", append = TRUE)
cat(paste("Название птицы с максимальным числом особей в одном наблюдении: ", max_individuals_bird), "\n", file = "Birds_analysis.txt", append = TRUE)

cat(paste("Максимальное количество наблюдений для одной птицы: ", max_observations), "\n", file = "Birds_analysis.txt", append = TRUE)
cat(paste("Среднее количество особей в одном наблюдении: ", mean_individuals_per_observation), "\n", file = "Birds_analysis.txt", append = TRUE)
# Добавьте еще три метрики (insights) на свое усмотрение и сохраните их с аргументом append = TRUE
