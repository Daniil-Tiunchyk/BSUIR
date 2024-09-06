package computer.games.controllers;

import computer.games.model.MedicalPerson;
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
  private TextField jobTitle;

  @FXML
  private TextField experience;

  @FXML
  private TextField dateOfEmployee;

  private Stage dialogStage;
  private MedicalPerson medicalPerson;
  private boolean okClicked = false;

  @FXML
  private void initialize() {}

  public void setDialogStage(Stage dialogStage) {
    this.dialogStage = dialogStage;
  }

  public void setPerson(MedicalPerson medicalPerson) {
    this.medicalPerson = medicalPerson;
    firstNameField.setText(medicalPerson.getFirstName());
    lastNameField.setText(medicalPerson.getLastName());
    jobTitle.setText(medicalPerson.getJobTitle());
    experience.setText(Integer.toString(medicalPerson.getPostalCode()));

    dateOfEmployee.setText(DateUtil.format(medicalPerson.getDateOfEmployee()));
    dateOfEmployee.setPromptText("dd.mm.yyyy");
  }

  public boolean isOkClicked() {
    return okClicked;
  }

  @FXML
  private void handleOk() {
    if (isInputValid()) {
      medicalPerson.setFirstName(firstNameField.getText());
      medicalPerson.setLastName(lastNameField.getText());

      medicalPerson.setPostalCode(Integer.parseInt(experience.getText()));

      medicalPerson.setDateOfEmployee(DateUtil.parse(dateOfEmployee.getText()));
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

    if (isFieldEmpty(jobTitle)) {
      errorMessage += "Некорректно введена должность!\n";
    }

    if (isFieldEmpty(experience)) {
      errorMessage += "Некорректно введен опыт работы!\n";
    } else {
      if (!isInteger(experience.getText())) {
        errorMessage +=
          "Некорректно введен опыт работы (должен быть целочисленным)!\n";
      }
    }

    if (isFieldEmpty(dateOfEmployee)) {
      errorMessage += "Некорректно введена дата найма!\n";
    } else {
      if (!DateUtil.validDate(dateOfEmployee.getText())) {
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
