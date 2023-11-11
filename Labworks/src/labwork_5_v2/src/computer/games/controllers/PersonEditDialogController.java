package computer.games.controllers;

import computer.games.model.Person;
import computer.games.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PersonEditDialogController {

  @FXML
  private TextField firstNameField;

  @FXML
  private TextField lastNameField;

  @FXML
  private TextField streetField;

  @FXML
  private TextField postalCodeField;

  @FXML
  private TextField cityField;

  @FXML
  private TextField birthdayField;

  private Stage dialogStage;
  private Person person;
  private boolean okClicked = false;

  @FXML
  private void initialize() {}

  public void setDialogStage(Stage dialogStage) {
    this.dialogStage = dialogStage;
  }

  public void setPerson(Person person) {
    this.person = person;
    firstNameField.setText(person.getFirstName());
    lastNameField.setText(person.getLastName());
    streetField.setText(person.getStreet());

    postalCodeField.setText(Integer.toString(person.getPostalCode()));
    cityField.setText(person.getCity());

    birthdayField.setText(DateUtil.format(person.getBirthday()));
    birthdayField.setPromptText("dd.mm.yyyy");
  }

  public boolean isOkClicked() {
    return okClicked;
  }

  @FXML
  private void handleOk() {
    if (isInputValid()) {
      person.setFirstName(firstNameField.getText());
      person.setLastName(lastNameField.getText());
      person.setStreet(streetField.getText());

      person.setPostalCode(Integer.parseInt(postalCodeField.getText()));

      person.setBirthday(DateUtil.parse(birthdayField.getText()));
      okClicked = true;
      dialogStage.close();
    }
  }

  @FXML
  private void handleCancel() {
    dialogStage.close();
  }

  private boolean isInputValid() {
    String errorMessage = "";

    if (isFieldEmpty(firstNameField)) {
      errorMessage += "Некорректно введено имя!\n";
    }

    if (isFieldEmpty(lastNameField)) {
      errorMessage += "Некорректно введена фамилия!\n";
    }

    if (isFieldEmpty(streetField)) {
      errorMessage += "Некорректно введена улица!\n";
    }

    if (isFieldEmpty(postalCodeField)) {
      errorMessage += "Некорректно введен почтовый код!\n";
    } else {
      if (!isInteger(postalCodeField.getText())) {
        errorMessage +=
          "Некорректно введен почтовый код (должен быть целочисленным)!\n";
      }
    }

    if (isFieldEmpty(cityField)) {
      errorMessage += "Некорректно введен город!\n";
    }

    if (isFieldEmpty(birthdayField)) {
      errorMessage += "Некорректно введен день рождения!\n";
    } else {
      if (!DateUtil.validDate(birthdayField.getText())) {
        errorMessage +=
          "Неверный формат даты. Используйте формат дд.мм.гггг!\n";
      }
    }

    if (errorMessage.isEmpty()) {
      return true;
    } else {
      showAlert(
        "Неверно заполнены поля",
        "Введите корректные значения полей",
        errorMessage,
        Alert.AlertType.WARNING
      );
      return false;
    }
  }

  private boolean isFieldEmpty(TextField field) {
    return field.getText() == null || field.getText().isEmpty();
  }

  private boolean isInteger(String s) {
    try {
      Integer.parseInt(s);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  private void showAlert(
    String title,
    String header,
    String content,
    Alert.AlertType alertType
  ) {
    Alert alert = new Alert(alertType);
    alert.initOwner(dialogStage);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);
    alert.showAndWait();
  }
}
