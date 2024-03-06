# Load necessary library
library(tidyverse)

# Load data
data <- read.csv("data/Tablitsa-zagryaznyajushih-veshestv-po-gorodam.csv", stringsAsFactors = FALSE)

# Function to clean and replace
clean_and_replace <- function(x) {
  x[x == "-"] <- NA
  x <- as.numeric(x)
  mean_value <- mean(x, na.rm = TRUE)
  x[is.na(x)] <- mean_value
  return(x)
}

# Apply function to year columns
year_cols <- c("2010", "2011", "2012", "2013", "2014")
data[year_cols] <- lapply(data[year_cols], clean_and_replace)

# Separate contaminants column
data <- separate(data, contaminants, into = c("contaminant_type", "contaminant_subtype"), sep = " ", remove = FALSE, convert = TRUE)

# Save cleaned table to new file
write.csv(data, "Tablitsa-zagryaznyajushih-veshestv-pogorodam_cleaned.csv", row.names = FALSE)

# Load data
data <- read.csv("data/Tablitsa-zagryaznyajushih-veshestv-v-atmosfernom-vozduhe-po-gorodam.csv", stringsAsFactors = FALSE)

# Replace "-" with NA and convert year columns to numeric
data[data == "–"] <- NA
year_cols <- c("X2005", "X2006", "X2007", "X2008", "X2009", "X2010", "X2011", "X2012", "X2013", "X2014")
data[year_cols] <- lapply(data[year_cols], as.numeric)

# Function to calculate pollution trend
calculate_trend <- function(x) {
  x[x == "–"] <- NA
  diff_value <- tail(na.omit(x), 1) - head(na.omit(x), 1)
  if (diff_value > 0) {
    return("increase")
  } else if (diff_value < 0) {
    return("decrease")
  } else {
    return("mixed")
  }
}

# Function to calculate relative change in pollution level
calculate_relative_change <- function(x) {
  x[x == "–"] <- NA
  relative_change <- ((tail(na.omit(x), 1) - head(na.omit(x), 1)) / head(na.omit(x), 1)) * 100
  return(relative_change)
}

# Add new columns with trend and relative change
data <- data %>% mutate(
  trend = apply(select(data, starts_with("X")), 1, calculate_trend),
  relative_change = apply(select(data, starts_with("X")), 1, calculate_relative_change)
)

# Save updated table to new file
write.csv(data, "Tablitsa-zagryaznyajushih-veshestv-v-atmosfernom-vozduhe-po-gorodam_processed.csv", row.names = FALSE)
