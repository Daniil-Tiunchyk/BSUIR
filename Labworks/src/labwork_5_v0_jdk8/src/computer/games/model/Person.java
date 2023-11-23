package computer.games.model;

import java.time.LocalDate;
import javafx.beans.property.*;

public class Person {

  private final StringProperty firstName;
  private final StringProperty lastName;
  private final StringProperty city;
  private final StringProperty street;
  private final IntegerProperty postalCode;
  private final ObjectProperty<LocalDate> birthday;

  public Person() {
    this(null, null);
  }

  public String getFirstName() {
    return firstName.get();
  }

  public StringProperty firstNameProperty() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName.set(firstName);
  }

  public String getLastName() {
    return lastName.get();
  }

  public StringProperty lastNameProperty() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName.set(lastName);
  }

  public String getCity() {
    return city.get();
  }

  public String getStreet() {
    return street.get();
  }

  public void setStreet(String street) {
    this.street.set(street);
  }

  public int getPostalCode() {
    return postalCode.get();
  }

  public void setPostalCode(int postalCode) {
    this.postalCode.set(postalCode);
  }

  public LocalDate getBirthday() {
    return birthday.get();
  }

  public void setBirthday(LocalDate birthday) {
    this.birthday.set(birthday);
  }

  public Person(String firstName, String lastName) {
    this.firstName = new SimpleStringProperty(firstName);
    this.lastName = new SimpleStringProperty(lastName);
    this.city = new SimpleStringProperty("г. Минск");
    this.street = new SimpleStringProperty("пр. Независимости");
    this.postalCode = new SimpleIntegerProperty(1234);
    this.birthday = new SimpleObjectProperty<>(LocalDate.of(2000, 1, 1));
  }
}
