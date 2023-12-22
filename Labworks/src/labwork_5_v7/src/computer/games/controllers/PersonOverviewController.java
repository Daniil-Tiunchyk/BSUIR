package computer.games.controllers;

import computer.games.MainApp;
import computer.games.model.MedicalPerson;
import computer.games.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PersonOverviewController {

  @FXML
  private TableView<MedicalPerson> personTable;

  @FXML
  private TableColumn<MedicalPerson, String> firstNameColumn;

  @FXML
  private TableColumn<MedicalPerson, String> lastNameColumn;

  @FXML
  private Label firstNameLabel;

  @FXML
  private Label lastNameLabel;

  @FXML
  private Label postalCodeLabel;

  @FXML
  private Label cityLabel;

  @FXML
  private Label birthdayLabel;

  private MainApp mainApp;

  public PersonOverviewController() {}

  @FXML
  private void initialize() {
    firstNameColumn.setCellValueFactory(cellData ->
      cellData.getValue().firstNameProperty()
    );
    lastNameColumn.setCellValueFactory(cellData ->
      cellData.getValue().lastNameProperty()
    );
    showPersonDetails(null);
    personTable
      .getSelectionModel()
      .selectedItemProperty()
      .addListener((observable, oldValue, newValue) ->
        showPersonDetails(newValue)
      );
  }

  public void setMainApp(MainApp mainApp) {
    this.mainApp = mainApp;
    personTable.setItems(mainApp.getPersonData());
  }

  private void showPersonDetails(MedicalPerson medicalPerson) {
    if (medicalPerson != null) {
      firstNameLabel.setText(medicalPerson.getFirstName());
      lastNameLabel.setText(medicalPerson.getLastName());

      postalCodeLabel.setText(Integer.toString(medicalPerson.getPostalCode()));
      cityLabel.setText(medicalPerson.getJobTitle());
      birthdayLabel.setText(DateUtil.format(medicalPerson.getDateOfEmployee()));
    } else {
      firstNameLabel.setText("");
      lastNameLabel.setText("");
      postalCodeLabel.setText("");
      cityLabel.setText("");
      birthdayLabel.setText("");
    }
  }

  @FXML
  private void handleDeletePerson() {
    int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0) personTable.getItems().remove(selectedIndex); else {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.initOwner(mainApp.getPrimaryStage());
      alert.setTitle("Не выбрана строка для удаления!");
      alert.setHeaderText("Предупреждение!");
      alert.setContentText("Выберите, пожалуйста, строку в таблице");
      alert.showAndWait();
    }
  }

  @FXML
  private void handleNewPerson() {
    MedicalPerson medicalPerson = new MedicalPerson();
    boolean okClicked = mainApp.showPersonEditDialog(medicalPerson);
    if (okClicked) mainApp.getPersonData().add(medicalPerson);
  }

  @FXML
  private void handleEditPerson() {
    MedicalPerson selectedMedicalPerson = personTable
      .getSelectionModel()
      .getSelectedItem();
    if (selectedMedicalPerson != null) {
      boolean okClicked = mainApp.showPersonEditDialog(selectedMedicalPerson);
      if (okClicked) showPersonDetails(selectedMedicalPerson);
    } else {
      showAlert(
        "Нет выбранной записи",
        "Не выбрана запись",
        "Выберите запись в таблице для редактирования",
        Alert.AlertType.WARNING
      );
    }
  }

  private void showAlert(
    String title,
    String header,
    String content,
    Alert.AlertType alertType
  ) {
    Alert alert = new Alert(alertType);
    alert.initOwner(mainApp.getPrimaryStage());
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);
    alert.showAndWait();
  }
}
