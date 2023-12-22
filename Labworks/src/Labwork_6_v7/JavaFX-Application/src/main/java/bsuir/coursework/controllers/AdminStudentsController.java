package bsuir.coursework.controllers;

import static bsuir.coursework.utils.ApiUtils.*;
import static bsuir.coursework.utils.JavaFXUtils.changeScene;
import static bsuir.coursework.utils.JavaFXUtils.showWarningAlert;

import bsuir.coursework.models.dto.UserDTO;
import bsuir.coursework.utils.ApiUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.http.HttpRequest;
import java.util.List;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;

public class AdminStudentsController {

  @FXML
  private TableColumn<UserDTO, String> columnEmail;

  @FXML
  private TableColumn<UserDTO, String> columnFirstName;

  @FXML
  private TableColumn<UserDTO, String> columnLastName;

  @FXML
  private TableColumn<UserDTO, String> columnPhoneNumber;

  @FXML
  private TableColumn<UserDTO, Number> columnStudentID;

  @FXML
  private TextField emailField;

  @FXML
  private TextField nameField;

  @FXML
  private PasswordField passwordField;

  @FXML
  private TextField surnameField;

  @FXML
  private TableView<UserDTO> tableStudents;

  @FXML
  private TextField telephoneField;

  @FXML
  void add(ActionEvent event) {
    if (validateFields()) {
      UserDTO userDTO = new UserDTO();
      userDTO.setEmail(emailField.getText());
      userDTO.setPassword(passwordField.getText());
      userDTO.setFirstName(nameField.getText());
      userDTO.setLastName(surnameField.getText());
      userDTO.setPhoneNumber(telephoneField.getText());

      setData(userDTO, "http://localhost:8080/api/users");
    } else {
      showWarningAlert("Пожалуйста, заполните все поля");
    }
  }

  @FXML
  void delete(ActionEvent event) {
    UserDTO selectedUser = tableStudents.getSelectionModel().getSelectedItem();

    if (selectedUser != null) {
      Integer id = selectedUser.getUserId();
      deleteData("http://localhost:8080/api/users/" + id);
    } else {
      showWarningAlert("Пожалуйста, выберите студента");
    }
  }

  @FXML
  void edit(ActionEvent event) {
    UserDTO userDTO = tableStudents.getSelectionModel().getSelectedItem();

    if (userDTO != null && validateFields()) {
      userDTO.setEmail(emailField.getText());
      userDTO.setPassword(passwordField.getText());
      userDTO.setFirstName(nameField.getText());
      userDTO.setLastName(surnameField.getText());
      userDTO.setPhoneNumber(telephoneField.getText());
      updateData(
        userDTO,
        "http://localhost:8080/api/users/" + userDTO.getUserId()
      );
    } else {
      showWarningAlert("Пожалуйста, заполните все поля");
    }
  }

  private boolean validateFields() {
    return (
      !emailField.getText().isEmpty() &&
      !passwordField.getText().isEmpty() &&
      !nameField.getText().isEmpty() &&
      !surnameField.getText().isEmpty() &&
      !telephoneField.getText().isEmpty()
    );
  }

  @FXML
  void logout(ActionEvent event) {
    MenuItem menuItem = (MenuItem) event.getSource();
    Node sourceNode = menuItem
      .getParentPopup()
      .getOwnerWindow()
      .getScene()
      .getRoot();
    changeScene("login.fxml", "Login", sourceNode);
  }

  public void loadData() {
    HttpRequest request = ApiUtils.loadData("http://localhost:8080/api/users");
    sendHttpRequestAsync(request, this::displayData);
  }

  private void displayData(String json) {
    Gson gson = new Gson();
    Type listType = new TypeToken<List<UserDTO>>() {}.getType();
    List<UserDTO> users = gson.fromJson(json, listType);
    ObservableList<UserDTO> data = FXCollections.observableArrayList(users);

    tableStudents.setItems(data);
  }

  @FXML
  void refreshTable(ActionEvent event) {
    loadData();
  }

  @FXML
  void initialize() {
    columnStudentID.setCellValueFactory(cellData ->
      new SimpleIntegerProperty(cellData.getValue().getUserId())
    );
    columnEmail.setCellValueFactory(cellData ->
      new SimpleStringProperty(cellData.getValue().getEmail())
    );
    columnFirstName.setCellValueFactory(cellData ->
      new SimpleStringProperty(cellData.getValue().getFirstName())
    );
    columnLastName.setCellValueFactory(cellData ->
      new SimpleStringProperty(cellData.getValue().getLastName())
    );
    columnPhoneNumber.setCellValueFactory(cellData ->
      new SimpleStringProperty(cellData.getValue().getPhoneNumber())
    );
    tableStudents
      .getSelectionModel()
      .selectedItemProperty()
      .addListener((observable, oldValue, newValue) -> {
        if (newValue != null) {
          emailField.setText(newValue.getEmail());
          nameField.setText(newValue.getFirstName());
          surnameField.setText(newValue.getLastName());
          telephoneField.setText(newValue.getPhoneNumber());
        }
      });

    loadData();
  }
}
