package computer.games.model;

import java.time.LocalDate;
import javafx.beans.property.*;

public class Correspondence {

  private final StringProperty name;
  private final StringProperty authorName;
  private final StringProperty city;
  private final IntegerProperty postalCode;
  private final ObjectProperty<LocalDate> dateOfPublish;

  public StringProperty cityProperty() {
    return city;
  }

  public void setCity(String city) {
    this.city.set(city);
  }

  public IntegerProperty postalCodeProperty() {
    return postalCode;
  }

  public ObjectProperty<LocalDate> dateOfPublishProperty() {
    return dateOfPublish;
  }

  public Correspondence() {
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

  public String getAuthorName() {
    return authorName.get();
  }

  public StringProperty authorNameProperty() {
    return authorName;
  }

  public void setAuthorName(String authorName) {
    this.authorName.set(authorName);
  }

  public String getCity() {
    return city.get();
  }

  public int getPostalCode() {
    return postalCode.get();
  }

  public void setPostalCode(int postalCode) {
    this.postalCode.set(postalCode);
  }

  public LocalDate getDateOfPublish() {
    return dateOfPublish.get();
  }

  public void setDateOfPublish(LocalDate dateOfPublish) {
    this.dateOfPublish.set(dateOfPublish);
  }

  public Correspondence(String name, String authorName) {
    this.name = new SimpleStringProperty(name);
    this.authorName = new SimpleStringProperty(authorName);
    this.city = new SimpleStringProperty("г. Минск");
    this.postalCode = new SimpleIntegerProperty(1234);
    this.dateOfPublish = new SimpleObjectProperty<>(LocalDate.of(2000, 1, 1));
  }
}
