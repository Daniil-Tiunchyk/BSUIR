package computer.games.model;

import javafx.beans.property.*;

public class Game {

  private final StringProperty name;
  private final IntegerProperty year;
  private final StringProperty genre;
  private final DoubleProperty rating;
  private final DoubleProperty price;

  public Game() {
    this(null, null);
  }

  public String getName() {
    return name.get();
  }

  public StringProperty nameProperty() {
    return name;
  }

  public void setName(String name) {
    this.name.set(name);
  }

  public int getYear() {
    return year.get();
  }

  public IntegerProperty yearProperty() {
    return year;
  }

  public void setYear(int year) {
    this.year.set(year);
  }

  public String getGenre() {
    return genre.get();
  }

  public StringProperty genreProperty() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre.set(genre);
  }

  public double getRating() {
    return rating.get();
  }

  public DoubleProperty ratingProperty() {
    return rating;
  }

  public void setRating(double rating) {
    this.rating.set(rating);
  }

  public double getPrice() {
    return price.get();
  }

  public DoubleProperty priceProperty() {
    return price;
  }

  public void setPrice(double price) {
    this.price.set(price);
  }

  public Game(String name, Integer year) {
    this.name = new SimpleStringProperty(name);
    this.year = new SimpleIntegerProperty(year);
    this.genre = new SimpleStringProperty("rpg");
    this.rating = new SimpleDoubleProperty(0.0);
    this.price = new SimpleDoubleProperty(10.0);
  }
}
