package com.example.labwork_5_v0.model;

import java.time.LocalDate;
//Пример №11. Поэтапное создание приложения с использованием библиотеки JavaFX
//Person.java
import javafx.beans.property.*;

/**
 * Класс-модель для адресата (Person)
 */
public class Person {

  private final StringProperty firstName;
  private final StringProperty lastName;
  private final StringProperty city;
  private final StringProperty street;
  private final IntegerProperty postalCode;
  private final ObjectProperty<LocalDate> birthday;

  /**
   * Конструктор по умолчанию
   */
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

  public StringProperty cityProperty() {
    return city;
  }

  public void setCity(String city) {
    this.city.set(city);
  }

  public String getStreet() {
    return street.get();
  }

  public StringProperty streetProperty() {
    return street;
  }

  public void setStreet(String street) {
    this.street.set(street);
  }

  public int getPostalCode() {
    return postalCode.get();
  }

  public IntegerProperty postalCodeProperty() {
    return postalCode;
  }

  public void setPostalCode(int postalCode) {
    this.postalCode.set(postalCode);
  }

  public LocalDate getBirthday() {
    return birthday.get();
  }

  public ObjectProperty<LocalDate> birthdayProperty() {
    return birthday;
  }

  public void setBirthday(LocalDate birthday) {
    this.birthday.set(birthday);
  }

  /**
   * Конструктор с некоторыми начальными данными.
   *
   * @param firstName
   * @param lastName
   */
  public Person(String firstName, String lastName) {
    this.firstName = new SimpleStringProperty(firstName);
    this.lastName = new SimpleStringProperty(lastName);
    this.city = new SimpleStringProperty("г. Минск");
    this.street = new SimpleStringProperty("пр. Независимости");
    this.postalCode = new SimpleIntegerProperty(1234);
    this.birthday =
      new SimpleObjectProperty<LocalDate>(LocalDate.of(2000, 01, 01));
  }
}
