library(dplyr)
# library(nycflights13)

flights <- readRDS("Data/flights.RData")

flights

# filter-----------

jan1 <- filter(flights, month == 1, day == 1)

(dec25 <- filter(flights, month == 12, day == 25))

# Floating point numbers
sqrt(2)^2 == 2
1 / 49 * 49 == 1

near(sqrt(2)^2, 2)
near(1 / 49 * 49, 1)

# The following code finds all flights that departed in November or December:
filter(flights, month == 11 | month == 12)

# It finds all months that equal 11 | 12, that evaluates to TRUE, i.e. 1 (January)
filter(flights, month == 11 | 12)

# %in%
nov_dec <- filter(flights, month %in% c(11, 12))

# Flights that weren’t delayed (on arrival or departure) by more than two hours
filter(flights, !(arr_delay > 120 | dep_delay > 120))
filter(flights, arr_delay <= 120, dep_delay <= 120)

# To preserve NA
df <- tibble(x = c(1, NA, 3))
filter(df, x > 1)
filter(df, is.na(x) | x > 1)

# arrange-----------

arrange(flights, year, month, day)

arrange(flights, desc(arr_delay))

# Missing values are always sorted at the end:
df <- tibble(x = c(5, 2, NA))
arrange(df, x)
arrange(df, desc(x))

# select-----------

select(flights, year, month, day)

# Select all columns between year and day (inclusive)
select(flights, year:day)

# Select all columns except those from year to day (inclusive)
select(flights, -(year:day))

# Rename variable
rename(flights, tail_num = tailnum)

# To move variables to the start of the data frame
select(flights, time_hour, air_time, everything())

# mutate-----------

flights_sml <- select(
  flights,
  year:day,
  ends_with("delay"),
  distance,
  air_time
)

mutate(flights_sml,
       gain = arr_delay - dep_delay,
       speed = distance / air_time * 60
)

# One can refer to columns that you’ve just created:
mutate(flights_sml,
       gain = arr_delay - dep_delay,
       hours = air_time / 60,
       gain_per_hour = gain / hours
)

# To keep only the new variables use transmute()
transmute(flights,
          gain = arr_delay - dep_delay,
          hours = air_time / 60,
          gain_per_hour = gain / hours
)

transmute(flights,
          dep_time,
          hour = dep_time %/% 100,
          minute = dep_time %% 100
)

# summarise----------

summarise(flights, delay = mean(dep_delay, na.rm = TRUE))

by_day <- group_by(flights, year, month, day)
View(summarise(by_day, delay = mean(dep_delay, na.rm = TRUE)))

# pipes--------------

by_dest <- group_by(flights, dest)
delay <- summarise(by_dest,
                   count = n(),
                   dist = mean(distance, na.rm = TRUE),
                   delay = mean(arr_delay, na.rm = TRUE)
)
delay <- filter(delay, count > 20, dest != "HNL")

# It looks like delays increase with distance up to ~750 miles
# and then decrease. Maybe as flights get longer there's more
# ability to make up delays in the air?
ggplot(data = delay, mapping = aes(x = dist, y = delay)) +
  geom_point(aes(size = count), alpha = 1 / 3) +
  geom_smooth(se = FALSE)

# The same with pipes:
delays <- flights %>%
  group_by(dest) %>%
  summarise(
    count = n(),
    dist = mean(distance, na.rm = TRUE),
    delay = mean(arr_delay, na.rm = TRUE)
  ) %>%
  filter(count > 20, dest != "HNL")


# Not cancelled
not_cancelled <- flights %>%
  filter(!is.na(dep_delay), !is.na(arr_delay))
# %>%
#   group_by(year, month, day) %>%
#   summarise(mean = mean(dep_delay))

delays <- not_cancelled %>%
  group_by(tailnum) %>%
  summarise(
    delay = mean(arr_delay)
  )

ggplot(data = delays, mapping = aes(x = delay)) +
  geom_freqpoly(binwidth = 10)

#
delays <- not_cancelled %>%
  group_by(tailnum) %>%
  summarise(
    delay = mean(arr_delay, na.rm = TRUE),
    n = n()
  )

ggplot(data = delays, mapping = aes(x = n, y = delay)) +
  geom_point(alpha = 1 / 10)

#
delays %>%
  filter(n > 25) %>%
  ggplot(mapping = aes(x = n, y = delay)) +
  geom_point(alpha = 1 / 10)


# To remove grouping
daily <- group_by(flights, year, month, day)
daily %>%
  ungroup() %>% # no longer grouped by date
  summarise(flights = n())
