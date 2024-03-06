# Установите необходимые библиотеки, если они еще не установлены
# install.packages("tidyverse")

# Загрузите библиотеки
library(tidyverse)

# Загрузите данные из файла
data <- read.csv("data/Tablitsa-zagryaznyajushih-veshestv-po-gorodam.csv", stringsAsFactors = FALSE)

# Функция для вычисления среднего значения и замены диапазона
clean_and_replace <- function(x) {
  # Замените "-" на NA
  x[x == "-"] <- NA
  
  # Преобразуйте столбец в числовой формат
  x <- as.numeric(x)
  
  # Вычислите среднее значение и замените NA на среднее
  mean_value <- mean(x, na.rm = TRUE)
  x[is.na(x)] <- mean_value
  
  return(x)
}

# Примените функцию к столбцам с годами (2010, 2011, 2012, 2013, 2014)
data[, c("2010", "2011", "2012", "2013", "2014")] <- apply(data[, c("2010", "2011", "2012", "2013", "2014")], 2, clean_and_replace)

# Разделите столбец contaminants на несколько столбцов
data_split <- separate(data, contaminants, into = c("contaminant_type", "contaminant_subtype"), sep = " ", remove = FALSE, convert = TRUE)

# Сохраните очищенную таблицу в новый файл
write.csv(data_split, "Tablitsa-zagryaznyajushih-veshestv-pogorodam_cleaned.csv", row.names = FALSE)

# Выведите первые строки очищенной таблицы
head(data_split)


# Загрузите данные из файла
data <- read.csv("data/Tablitsa-zagryaznyajushih-veshestv-v-atmosfernom-vozduhe-po-gorodam.csv", stringsAsFactors = FALSE)

# Выведите имеющиеся колонки
print(colnames(data))


# Замените "-" на NA
data[data == "–"] <- NA

# Конвертируйте столбцы с годами в числа
data[, c("X2005", "X2006", "X2007", "X2008", "X2009", "X2010", "X2011", "X2012", "X2013", "X2014")] <- 
  lapply(data[, c("X2005", "X2006", "X2007", "X2008", "X2009", "X2010", "X2011", "X2012", "X2013", "X2014")], as.numeric)

# Выведите имеющиеся колонки
print(colnames(data))

# Функция для вычисления абсолютного тренда загрязнения
calculate_trend <- function(x) {
  print(x)
  # Замените "-" на NA
  x[x == "–"] <- NA
  
  # Вычислите разницу между первым и последним значением
  diff_value <- tail(na.omit(as.numeric(x)), 1) - head(na.omit(as.numeric(x)), 1)
  print(diff_value)
  
  # Определите тип тренда
  if (diff_value > 0) {
    return("increase")
  } else if (diff_value < 0) {
    return("decrease")
  } else {
    return("mixed")
  }
}

# Функция для вычисления относительного изменения уровня загрязнения
calculate_relative_change <- function(x) {
  print(x)
  # Замените "-" на NA
  x[x == "–"] <- NA
  
  # Вычислите относительное изменение между первым и последним значением
  relative_change <- ((tail(na.omit(as.numeric(x)), 1) - head(na.omit(as.numeric(x)), 1)) / head(na.omit(as.numeric(x)), 1)) * 100
  print(relative_change)
  
  return(relative_change)
}

# Добавьте новые столбцы с абсолютным трендом и относительным изменением
data <- data %>%
  mutate(trend = apply(select(data, starts_with("X")), 1, calculate_trend),
         relative_change = apply(select(data, starts_with("X")), 1, calculate_relative_change))

# Сохраните обновленную таблицу в новый файл
write.csv(data, "Tablitsa-zagryaznyajushih-veshestv-v-atmosfernom-vozduhe-po-gorodam_processed.csv", row.names = FALSE)

# Выведите первые строки обновленной таблицы
head(data)