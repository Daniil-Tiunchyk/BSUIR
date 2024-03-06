# Load necessary library
library(dplyr)

# Load data
data <- read.csv("Birds.csv")

# Add columns and fill values
data <- data %>%
    mutate(
        individualsPerObservation = individualsNumber / observations,
        speciesShare = individualsNumber / sum(individualsNumber)
    )

# Sort data by individualsNumber in descending order
sorted_data <- data %>%
    arrange(desc(individualsNumber))

# Save modified table to file
write.csv(sorted_data, "Birds.csv_updated.csv", row.names = FALSE)

# Calculate necessary data
total_observed_birds <- sum(data$individualsNumber)
median_observations <- median(data$observations)
bird_counts_ranges <- table(cut(data$individualsNumber, breaks = c(0, 10, 50, 100, 500, 1000, Inf)))
max_individuals_bird <- data[which.max(data$individualsNumber), ]$speciesName

# Additional metrics (insights)
max_observations <- max(data$observations)
mean_individuals_per_observation <- mean(data$individualsPerObservation, na.rm = TRUE)

# Save results to file
cat(
    paste(
        "Total observed birds: ", total_observed_birds, "\n",
        "Median observations: ", median_observations, "\n",
        "Bird counts ranges: ", toString(bird_counts_ranges), "\n",
        "Bird with max individuals: ", max_individuals_bird, "\n",
        "Max observations for a bird: ", max_observations, "\n",
        # Fix NA
        # "Mean individuals per observation: ", mean_individuals_per_observation
    ),
    file = "Birds_analysis.txt"
)
