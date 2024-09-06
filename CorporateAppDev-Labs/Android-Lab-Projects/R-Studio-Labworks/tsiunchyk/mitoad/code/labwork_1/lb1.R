# Установка пакетов
install.packages(c("plyr", "ggplot2", "ggthemes", "knitr", "highcharter", "stringi", "sp"))

# Убедитесь, что все пакеты из списка установлены
required_packages <- c("cluster", "dplyr", "ggplot2", "ggthemes", "highcharter", "knitr", "lubridate", 
                       "plyr", "rmarkdown", "sp", "stats", "stringi", "tidyr", "xts")

# Установка отсутствующих пакетов
install_missing_packages <- required_packages[!(required_packages %in% installed.packages()[,"Package"])]
if (length(in
# Создание файла packages.txt
installed_packages <- as.data.frame(installed.packages())
installed_packages <- installed_packages[,c("Package", "Version")]

# Создание папки docs, если её нет
if (!file.exists("docs")) {
  dir.create("docs")
}

# Запись списка установленных пакетов в файл
write.table(installed_packages, file = "docs/packages.txt", quote = FALSE, row.names = FALSE, col.names = TRUE, sep = "\t")
stall_missing_packages) > 0) {
  install.packages(install_missing_packages)
}
