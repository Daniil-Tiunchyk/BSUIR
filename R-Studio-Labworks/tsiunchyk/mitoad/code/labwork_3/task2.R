png(file = "lr3_2.png", width = 800, height = 600)

x.norm <- rnorm(n = 200, mean = 0, sd = 10)

par(mfrow = c(1, 1)) # Установка одного графика на страницу

hist(x.norm, breaks = 15, main = "Нормальное распределение", 
     xlab = "Значения", ylab = "Частота", border = "blue", col = "green")

dens <- density(x.norm)
lines(dens, col = "red", lwd = 2)

dev.off()
