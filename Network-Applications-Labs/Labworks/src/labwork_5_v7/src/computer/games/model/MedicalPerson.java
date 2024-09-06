package computer.games.model;

import java.time.LocalDate;
import javafx.beans.property.*;

public class MedicalPerson {

  private final StringProperty firstName;
  private final StringProperty lastName;
  private final StringProperty jobTitle;
  private final IntegerProperty experience;
  private final ObjectProperty<LocalDate> dateOfEmployee;

  public MedicalPerson() {
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

  public String getJobTitle() {
    return jobTitle.get();
  }

  public int getPostalCode() {
    return experience.get();
  }

  public void setPostalCode(int postalCode) {
    this.experience.set(postalCode);
  }

  public LocalDate getDateOfEmployee() {
    return dateOfEmployee.get();
  }

  public void setDateOfEmployee(LocalDate dateOfEmployee) {
    this.dateOfEmployee.set(dateOfEmployee);
  }

  public MedicalPerson(String firstName, String lastName) {
    this.firstName = new SimpleStringProperty(firstName);
    this.lastName = new SimpleStringProperty(lastName);
    this.jobTitle = new SimpleStringProperty("г. Минск");
    this.experience = new SimpleIntegerProperty(15);
    this.dateOfEmployee = new SimpleObjectProperty<>(LocalDate.of(2000, 1, 1));
  }
}
