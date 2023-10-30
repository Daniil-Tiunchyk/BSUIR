package com.example.labwork_5_v0.controllers;

import com.example.labwork_5_v0.MainApp;
import com.example.labwork_5_v0.model.Person;
import com.example.labwork_5_v0.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PersonOverviewController {

  @FXML
  private TableView<Person> personTable;

  @FXML
  private TableColumn<Person, String> firstNameColumn;

  @FXML
  private TableColumn<Person, String> lastNameColumn;

  @FXML
  private Label firstNameLabel;

  @FXML
  private Label lastNameLabel;

  @FXML
  private Label streetLabel;

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

  private void showPersonDetails(Person person) {
    if (person != null) {
      firstNameLabel.setText(person.getFirstName());
      lastNameLabel.setText(person.getLastName());
      streetLabel.setText(person.getStreet());

      postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
      cityLabel.setText(person.getCity());
      birthdayLabel.setText(DateUtil.format(person.getBirthday()));
    } else {
      firstNameLabel.setText("");
      lastNameLabel.setText("");
      streetLabel.setText("");
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
    Person person = new Person();
    boolean okClicked = mainApp.showPersonEditDialog(person);
    if (okClicked) mainApp.getPersonData().add(person);
  }

  @FXML
  private void handleEditPerson() {
    Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
    if (selectedPerson != null) {
      boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
      if (okClicked) showPersonDetails(selectedPerson);
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
