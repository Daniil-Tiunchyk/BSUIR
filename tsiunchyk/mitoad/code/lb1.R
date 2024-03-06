# List of required packages
required_packages <- c(
  "cluster", "dplyr", "ggplot2", "ggthemes", "highcharter", "knitr", "lubridate",
  "plyr", "rmarkdown", "sp", "stats", "stringi", "tidyr", "xts"
)

# Install missing packages
for (pkg in required_packages) {
  if (!require(pkg, character.only = TRUE)) {
    install.packages(pkg)
  }
}

# Get installed packages
installed_packages <- as.data.frame(installed.packages())
installed_packages <- installed_packages[, c("Package", "Version")]

# Create docs directory and write packages.txt
if (!dir.exists("docs")) {
  dir.create("docs")
  write.csv(installed_packages, file = "docs/packages.csv", row.names = FALSE)
}
