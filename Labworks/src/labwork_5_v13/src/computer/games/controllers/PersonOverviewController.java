package computer.games.controllers;

import computer.games.MainApp;
import computer.games.model.Correspondence;
import computer.games.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PersonOverviewController {

  @FXML
  private TableView<Correspondence> personTable;

  @FXML
  private TableColumn<Correspondence, String> firstNameColumn;

  @FXML
  private TableColumn<Correspondence, String> lastNameColumn;

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
      cellData.getValue().nameProperty()
    );
    lastNameColumn.setCellValueFactory(cellData ->
      cellData.getValue().authorNameProperty()
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

  private void showPersonDetails(Correspondence correspondence) {
    if (correspondence != null) {
      firstNameLabel.setText(correspondence.getName());
      lastNameLabel.setText(correspondence.getAuthorName());

      postalCodeLabel.setText(Integer.toString(correspondence.getPostalCode()));
      cityLabel.setText(correspondence.getCity());
      birthdayLabel.setText(DateUtil.format(correspondence.getDateOfPublish()));
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
    Correspondence correspondence = new Correspondence();
    boolean okClicked = mainApp.showPersonEditDialog(correspondence);
    if (okClicked) mainApp.getPersonData().add(correspondence);
  }

  @FXML
  private void handleEditPerson() {
    Correspondence selectedCorrespondence = personTable
      .getSelectionModel()
      .getSelectedItem();
    if (selectedCorrespondence != null) {
      boolean okClicked = mainApp.showPersonEditDialog(selectedCorrespondence);
      if (okClicked) showPersonDetails(selectedCorrespondence);
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
