# Загружаем необходимую библиотеку
library(ggplot2)

# Загружаем dataset mpg
data(mpg)

# 1. Построение диаграммы рассеяния hwy от cyl
ggplot(data = mpg) +
  geom_point(mapping = aes(x = cyl, y = hwy))

# Диаграмма рассеяния с параметром position = "jitter"
ggplot(data = mpg) +
  geom_point(mapping = aes(x = cyl, y = hwy), position = "jitter")

# 2. Исправление кода, чтобы точки были синие
ggplot(data = mpg) +
  geom_point(mapping = aes(x = displ, y = hwy), color = "blue")

# 3. Отображение переменных с помощью color, size и shape
# Использование color с качественной переменной
ggplot(data = mpg) +
  geom_point(mapping = aes(x = displ, y = hwy, color = factor(cyl)))

# Использование size с количественной переменной
ggplot(data = mpg) +
  geom_point(mapping = aes(x = displ, y = hwy, size = hwy))

# Использование shape с качественной переменной
ggplot(data = mpg) +
  geom_point(mapping = aes(x = displ, y = hwy, shape = factor(cyl)))

# 4. Отображение одной и той же переменной через несколько aesthetics
ggplot(data = mpg) +
  geom_point(mapping = aes(x = displ, y = hwy, color = factor(cyl), size = cyl))

# 5. Stroke aesthetic предназначена для управления шириной границы (обводки) точек
ggplot(data = mpg) +
  geom_point(mapping = aes(x = displ, y = hwy, color = factor(cyl)), stroke = 2)

# 6. Использование условного выражения в качестве aesthetic
ggplot(data = mpg) +
  geom_point(mapping = aes(x = displ, y = hwy, colour = displ < 5))

# 7. Построение facet по количественной переменной
ggplot(data = mpg) +
  geom_point(mapping = aes(x = displ, y = hwy)) +
  facet_wrap(~ hwy)

# 8. Параметр . (точка) в facet_grid
# . в drv ~ . означает, что фасеты будут распределены по строкам
ggplot(data = mpg) +
  geom_point(mapping = aes(x = displ, y = hwy)) +
  facet_grid(drv ~ .)

# . в . ~ cyl означает, что фасеты будут распределены по столбцам
ggplot(data = mpg) +
  geom_point(mapping = aes(x = displ, y = hwy)) +
  facet_grid(. ~ cyl)

# 12. Улучшение графика
ggplot(data = mpg, mapping = aes(x = cty, y = hwy)) +
  geom_point(alpha = 0.5, color = "blue") +
  theme_minimal()

# 13. Преобразование stacked bar chart в круговую диаграмму
ggplot(data = mpg, mapping = aes(x = "", fill = factor(cyl))) +
  geom_bar(width = 1) +
  coord_polar(theta = "y")

# 15. Анализ графика взаимоотношения потребления топлива на трассе и в городе
ggplot(data = mpg, mapping = aes(x = cty, y = hwy)) +
  geom_point() +
  geom_abline(intercept = 0, slope = 1, colour = "red") + # добавим красную линию y=x
  coord_fixed(ratio = 1) # Важен для сохранения одинакового масштаба осей
