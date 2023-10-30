package com.example.labwork_5_v0.controllers;

//Пример №11. Поэтапное создание приложения с использованием библиотеки JavaFX
//PersonEditDialogController.java

import com.example.labwork_5_v0.model.Person;
import com.example.labwork_5_v0.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/*Окно для изменения информации об адресате*/
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

  /**
   * Инициализирует класс-контроллер. Этот метод вызывается
   * автоматически после того, как fxml-файл будет загружен.
   */
  @FXML
  private void initialize() {}

  /**
   * Устанавливает сцену для этого окна.
   *
   * @param dialogStage
   */
  public void setDialogStage(Stage dialogStage) {
    this.dialogStage = dialogStage;
  }

  /**
   * Задаёт данные об адресате, информацию о котором будем
   * менять.
   *
   * @param person
   */
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

  /**
   * Returns true, если пользователь кликнул OK, в другом случае
   * false.
   *
   * @return
   */
  public boolean isOkClicked() {
    return okClicked;
  }

  /**
   * Вызывается, когда пользователь кликнул по кнопке OK.
   */
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

  /**
   * Вызывается, когда пользователь кликнул по кнопке Cancel.
   */
  @FXML
  private void handleCancel() {
    dialogStage.close();
  }

  /**
   * Метод проверяет пользовательский ввод в текстовых полях.
   *
   * @return true, если пользовательский ввод корректен
   */
  private boolean isInputValid() {
    String errorMessage = "";
    if (
      firstNameField.getText() == null || firstNameField.getText().length() == 0
    ) {
      errorMessage += "Некорректно введено имя!\n";
    }
    if (
      lastNameField.getText() == null || lastNameField.getText().length() == 0
    ) {
      errorMessage += "Некорректно введена фамилия!\n";
    }
    if (streetField.getText() == null || streetField.getText().length() == 0) {
      errorMessage += "Некорректно введена улица!\n";
    }
    if (
      postalCodeField.getText() == null ||
      postalCodeField.getText().length() == 0
    ) {
      errorMessage += "Некорректно введен почтовый код!\n";
    } else {
      // try to parse the postal code into an int.
      try {
        Integer.parseInt(postalCodeField.getText());
      } catch (NumberFormatException e) {
        errorMessage +=
          "Некорректно введен почтовый код(должен быть целочисленным)!\n";
      }
    }
    if (cityField.getText() == null || cityField.getText().length() == 0) {
      errorMessage += "Некорректно введен город!\n";
    }
    if (
      birthdayField.getText() == null || birthdayField.getText().length() == 0
    ) {
      errorMessage += "Некорректно введен день рождения!\n";
    } else {
      if (!DateUtil.validDate(birthdayField.getText())) {
        errorMessage +=
          "Неверный формат даты. Используйте формат дд.мм.гггг!\n";
      }
    }
    if (errorMessage.length() == 0) {
      return true;
    } else {
      // Show the error message.
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.initOwner(dialogStage);
      alert.setTitle("Неверно заполнены поля");
      alert.setHeaderText("Введите корректные значения полей");
      alert.setContentText(errorMessage);
      alert.showAndWait();
      return false;
    }
  }
}
