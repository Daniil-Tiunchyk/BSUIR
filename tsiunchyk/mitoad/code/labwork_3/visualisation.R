library(dplyr)
library(tidyr)
library(ggplot2)
library(maps)

#----

# Plot() ----------

plot(1:20, main = "Заголовок")
legend("topleft", pch = 1, legend = "Twenty dots")
title(main = "Title") #conflict

plot(cars)

plot(trees)

# Graphics devices for BMP, JPEG, PNG and TIFF format bitmap files
png(file = "1-20.png", bg = "transparent")
plot(1:20)
dev.off()

# par() can be used to set or query graphical parameters
old.par <- par(mfrow = c(2, 1))
hist(cars$speed, main = "")
hist(cars$dist, main = "")
par(old.par)

plot(1:20)
text(locator(), "Locator", pos = 4)


# Данные (Kwan K.C. et al., 1976) по скорости выведения из организма человека 
# индометацина – одного из наиболее активных противовоспалительных препаратов. 
# В эксперименте приняли участие шесть испытуемых.

data(Indometh)

# Subject (испытуемый), time (время с момента введения препарата) 
# и conc (концентрация препарата в крови).

time

# The database is attached to the R search path. This means that the database 
# is searched by R when evaluating a variable, so objects in the database 
# can be accessed by simply giving their names.
attach(Indometh)
time

# Зависимость концентрации индометацина в крови от времени
plot(time, conc)

# Нужно отобразить средние значения концентрации индометацина для каждой временной точки
unique(time)
(means <- tapply(conc, time, mean))
names(means)

plot(names(means), means)

# type - type of plot
# "p" for points,
# "l" for lines,
# "b" for both,
# "c" for the lines part alone of "b",
# "o" for both ‘overplotted’,
# "h" for ‘histogram’ like (or ‘high-density’) vertical lines,
# "s" for stair steps,
# "S" for other steps, see ‘Details’ below,
# "n" for no plotting.
plot(names(means), means, type = "b")

# xlab, ylab - labels
plot(names(means), means, xlab = "Time", ylab = "Concentration")

# xlim, ylim - axis range
plot(names(means), means, xlab = "Time", ylab = "Concentration", xlim=c(0, 15), ylim=c(0, 5))

# axes, ann  - visibility of axes and labels
plot(names(means), means, xlab = "Time", ylab = "Concentration", axes = TRUE, ann = TRUE)
plot(names(means), means, xlab = "Time", ylab = "Concentration", axes = FALSE, ann = TRUE)
plot(names(means), means, xlab = "Time", ylab = "Concentration", axes = TRUE, ann = FALSE)
plot(names(means), means, xlab = "Time", ylab = "Concentration", axes = FALSE, ann = FALSE)

# log - transforms the axis to a logarithmic scale
plot(names(means), means, xlab = "Time", ylab = "Concentration", log = "x")

# log(means)
# exp(1)^0.73076404
plot(names(means), means, xlab = "Time", ylab = "Concentration", log = "y")
plot(names(means), log(means), xlab = "Time", ylab = "Concentration")

plot(names(means), means, xlab = "Time", ylab = "Concentration", log = "xy")

# main - title
plot(names(means), means, xlab = "Time", ylab = "Concentration", main = "The rate of indomethacin excretion")

# pch - plotting character [0:25]
plot(names(means), means, xlab = "Time", ylab = "Concentration",  pch = 8)
plot(names(means), means, xlab = "Time", ylab = "Concentration",  pch = 0:10)

# View all the shapes
y <- rev(c(rep(1, 6), rep(2, 5), rep(3, 5), rep(4, 5), rep(5, 5)))
x <- c(rep(1:5, 5), 6)
plot(x, y, pch = 0:25, cex = 1.5, ylim = c(1, 5.5), xlim = c(1, 6.5), 
     axes = FALSE, xlab = "", ylab = "", bg = "red")
text(x, y, labels = 0:25, pos = 4)

# Font of markers pch[1:128, 160:254]
plot(names(means), means, xlab = "Time", ylab = "Concentration",  pch = 169, font = 5)

# Letters as pch
plot(names(means), means, xlab = "Time", ylab = "Concentration",  pch = ":)")

# cex - character extension
# lwd - line width
# col - colour ["red" | "#RRGGBB" | 2]
# bg - filling colour of plotting characters 21:25
colours()

n <- 10
plot(1:n, pch = 21, cex = 1:n, col = 1:n, lwd = 1:n)
text(1:n)

plot(21:25, pch = 21:25, cex = 7, col = 9, lwd = 2, bg = 2:6)

# col.main - title colour
# col.lab - axis labels colour
plot(names(means), means, xlab = "Time", ylab = "Concentration", 
     main = "The rate of indomethacin excretion",  
     pch = 169, font = 5, col = "orchid3", cex = 3,
     col.main = "red", col.lab = 4)

# lend - line end [0, 1, 2]
# ljoin - line & join [0, 1, 2]
# lty - line & type (pattern of 4 numbers [1:9] "stroke - space - stroke - space")

plot(names(means), means, type = "l", lend = 0, lwd = 20)
plot(names(means), means, type = "l", lend = 1, lwd = 20)
plot(names(means), means, type = "l", lend = 2, lwd = 20)

plot(names(means), means, type = "l", lwd = 5, lty = 1)
plot(names(means), means, type = "l", lwd = 5, lty = 2)
plot(names(means), means, type = "l", lwd = 5, lty = 3)
plot(names(means), means, type = "l", lwd = 5, lty = 4)
plot(names(means), means, type = "l", lwd = 5, lty = 5)
plot(names(means), means, type = "l", lwd = 5, lty = 6)
plot(names(means), means, type = "l", lwd = 5, lty = 7)
plot(names(means), means, type = "l", lwd = 5, lty = "1919", col = 4)

# bty - box & type ["O" | "L" | "7" | "C" | "U" | "["]
plot(names(means), means, type = "l", bty = "7")

# histogram() ------------

# Нормально распределенная совокупность X из 100 наблюдений 
# со средним значением 15 и стандартным отклонением 5:
X <- rnorm(n = 100, mean = 15, sd = 5)
hist(X)

# breaks - number of columns
hist(X, breaks = 20, col = "lightblue")

# freq - frequency (by default TRUE). 
# If FALSE y-axis will reflect the probability density of each class so 
# that the total area under the histogram will be 1
hist(X, breaks = 20, freq = FALSE, col = "lightblue")

# density() - the curve of probability density
# bw - bandwidth (the smoothing bandwidth) >0
X <- rnorm(n = 50, mean = 15, sd = 5)
hist(X, breaks = 20, freq = FALSE, col = "lightblue")
plot(density(X))
plot(density(X, bw = 0.8))

hist(X, breaks = 20, freq = FALSE, col = "lightblue",
     xlab = "X",
     ylab = "Probability density ",
     main = "Histogram, combined with the probability density curve")
lines(density(X), col = "red", lwd = 2)
lines(density(X, bw = 0.8), col = "blue", lwd = 2)

# barplot() --------
# Эффективность шести видов инсектицидных средств
data(InsectSprays)
InsectSprays
attach(InsectSprays)
sprays_means <- tapply(count, spray, mean)
sprays_means

barplot(sprays_means)

# Params
# width - width of bar
# horiz - horizontal direction
# las - direction of y-labels
# density - a vector giving the density of shading lines
# angle - the slope of shading lines, given as an angle in degrees (counter-clockwise)
# space - the amount of space (as a fraction of the average bar width) left before each bar
barplot(sprays_means, col = "steelblue",
        xlab = "Insecticide",
        ylab = "Avg number of surviving insects",
        border = "red", width = sqrt(sprays_means))

barplot(sprays_means, density = 20, 
        col = "red", horiz = TRUE, las = 1,
        ylab = "Insecticide",
        xlab = "Avg number of surviving insects")

barplot(sprays_means, density = 20, 
        col = "red", horiz = TRUE, las = 1,
        angle = -45, space = 3,
        ylab = "Insecticide",
        xlab = "Avg number of surviving insects")

# dotchart() ---------

# Данные распределения 32 моделей автомобилей 1973-1974 годов выпуска по экономичности
# двигателя (количество миль, проезжаемых на одном галлоне топлива). 
# Данные были опубликованы в американском журнале Motor Trend в 1974 г. 
# и входят в стандартный набор данных R (data(mtcars)).
data(mtcars)

dotchart(mtcars$mpg, labels = row.names(mtcars),
         main="Efficiency in fuel consumption",
         xlab="Miles per gallon", cex = 0.8)

# sort
x <- mtcars %>%
  mutate(cars = rownames(mtcars)) %>%
  arrange(mpg) %>%
  mutate(color = cyl) %>%
  mutate(cyl = factor(cyl))

dotchart(x$mpg, labels = x$cars,
         groups = x$cyl, gcolor = "blue", pch = 16,
         main="Efficiency in fuel consumption",
         xlab="Miles per gallon", cex = 0.8, color = x$color)

# ggplot2 ---------

# mpg contains observations collected by the US Environment Protection Agency on 38 models of cars
mpg

# Mapping and aesthetic
ggplot(data = mpg) + 
  geom_point(mapping = aes(x = displ, y = hwy))


ggplot(data = mpg) + 
  geom_point(mapping = aes(x = displ, y = hwy, color = class))


ggplot(data = mpg) + 
  geom_point(mapping = aes(x = displ, y = hwy, size = class))


ggplot(data = mpg) + 
  geom_point(mapping = aes(x = displ, y = hwy, alpha = class))


ggplot(data = mpg) + 
  geom_point(mapping = aes(x = displ, y = hwy, shape = class))


ggplot(data = mpg) + 
  geom_point(mapping = aes(x = displ, y = hwy), color = "blue")


# Facets
ggplot(data = mpg) + 
  geom_point(mapping = aes(x = displ, y = hwy)) + 
  facet_wrap(~ class, nrow = 2)


ggplot(data = mpg) + 
  geom_point(mapping = aes(x = displ, y = hwy)) + 
  facet_grid(drv ~ cyl)


ggplot(data = mpg) + 
  geom_point(mapping = aes(x = displ, y = hwy)) +
  facet_grid(. ~ cyl)


# Geoms
ggplot(data = mpg) + 
  geom_smooth(mapping = aes(x = displ, y = hwy))


ggplot(data = mpg) + 
  geom_smooth(mapping = aes(x = displ, y = hwy, linetype = drv))


ggplot(data = mpg) +
  geom_smooth(mapping = aes(x = displ, y = hwy, group = drv))


# Multiple geoms
ggplot(data = mpg) + 
  geom_point(mapping = aes(x = displ, y = hwy)) +
  geom_smooth(mapping = aes(x = displ, y = hwy))


# Global mapping
ggplot(data = mpg, mapping = aes(x = displ, y = hwy)) + 
  geom_point() + 
  geom_smooth()


ggplot(data = mpg, mapping = aes(x = displ, y = hwy)) + 
  geom_point(mapping = aes(color = class)) + 
  geom_smooth()


ggplot(data = mpg, mapping = aes(x = displ, y = hwy)) + 
  geom_point(mapping = aes(color = class)) + 
  geom_smooth(data = filter(mpg, class == "subcompact"), se = FALSE)


# geom_bar

# The diamonds dataset comes in ggplot2 and contains information 
# about ~54,000 diamonds, including the price, carat, color, 
# clarity, and cut of each diamond
diamonds

ggplot(data = diamonds) + 
  geom_bar(mapping = aes(x = cut))

ggplot(data = diamonds) + 
  stat_count(mapping = aes(x = cut))


# Colour
ggplot(data = diamonds) + 
  geom_bar(mapping = aes(x = cut, colour = cut))

ggplot(data = diamonds) + 
  geom_bar(mapping = aes(x = cut, fill = cut))

ggplot(data = diamonds) + 
  geom_bar(mapping = aes(x = cut, fill = clarity))

# Stacking

# position = "identity" will place each object exactly where it falls.
# To see that overlapping we either need to make the bars slightly transparent 
# by setting alpha to a small value, or completely transparent by setting fill = NA.

ggplot(data = diamonds, mapping = aes(x = cut, fill = clarity)) + 
  geom_bar(alpha = 1/5, position = "identity")

ggplot(data = diamonds, mapping = aes(x = cut, colour = clarity)) + 
  geom_bar(fill = NA, position = "identity")

# position = "fill" works like stacking, but makes each set of stacked bars 
# the same height. This makes it easier to compare proportions across groups.

ggplot(data = diamonds) + 
  geom_bar(mapping = aes(x = cut, fill = clarity), position = "fill")

# position = "dodge" places overlapping objects directly beside one another. 
# This makes it easier to compare individual values.

ggplot(data = diamonds) + 
  geom_bar(mapping = aes(x = cut, fill = clarity), position = "dodge")

# position = "jitter" adds a small amount of random noise to each point. 
# This spreads the points out because no two points are likely to receive 
# the same amount of random noise.

# Displays only 126 points, even though there are 234 observations in the dataset.
ggplot(data = mpg) + 
  geom_point(mapping = aes(x = displ, y = hwy))

ggplot(data = mpg) + 
  geom_point(mapping = aes(x = displ, y = hwy), position = "jitter")


# Coordinates

# coord_flip() switches the x and y axes. This is useful (for example), 
# if you want horizontal boxplots. It’s also useful for long labels: 
# it’s hard to get them to fit without overlapping on the x-axis.

ggplot(data = mpg, mapping = aes(x = class, y = hwy)) + 
  geom_boxplot()

ggplot(data = mpg, mapping = aes(x = class, y = hwy)) + 
  geom_boxplot() +
  coord_flip()

# coord_quickmap() sets the aspect ratio correctly for maps. 
# This is very important if you’re plotting spatial data with ggplot2.

nz <- map_data("nz")

ggplot(nz, aes(long, lat, group = group)) +
  geom_polygon(fill = "white", colour = "black")

ggplot(nz, aes(long, lat, group = group)) +
  geom_polygon(fill = "white", colour = "black") +
  coord_quickmap()

w <- map_data("world2")
ggplot(w, aes(long, lat, group = group)) +
  geom_polygon(fill = "white", colour = "black") +
  coord_quickmap()

# coord_polar() uses polar coordinates. Polar coordinates reveal 
# an interesting connection between a bar chart and a Coxcomb chart.

bar <- ggplot(data = diamonds) + 
  geom_bar(
    mapping = aes(x = cut, fill = cut), 
    show.legend = FALSE,
    width = 1
  ) + 
  theme(aspect.ratio = 1) +
  labs(x = NULL, y = NULL)

bar + coord_flip()
bar + coord_polar()
