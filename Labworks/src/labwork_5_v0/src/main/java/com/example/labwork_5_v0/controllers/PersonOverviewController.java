package com.example.labwork_5_v0.controllers;

//Пример №11. Поэтапное создание приложения с использованием библиотеки JavaFX

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

  // Ссылка на главное приложение
  private MainApp mainApp;

  /**
   * Конструктор.
   * Конструктор вызывается раньше метода initialize().
   */
  public PersonOverviewController() {}

  /*Инициализация класса-контроллера. Этот метод вызывается
   автоматически после того, как fxml-файл будет загружен*/
  //Пример №11. Поэтапное создание приложения с использованием библиотеки JavaFX
  //Файл PersonOverviewController.java
  /*Инициализация класса-контроллера. Этот метод вызывается
автоматически после того, как fxml-файл будет загружен.*/
  @FXML
  private void initialize() {
    // Инициализация таблицы адресатов с двумя столбцами
    firstNameColumn.setCellValueFactory(cellData ->
      cellData.getValue().firstNameProperty()
    );
    lastNameColumn.setCellValueFactory(cellData ->
      cellData.getValue().lastNameProperty()
    );
    // Очистка дополнительной информации об адресате
    showPersonDetails(null);
    // Слушаем изменения выбора, и при изменении отображаем дополнительную информацию об адресате
    personTable
      .getSelectionModel()
      .selectedItemProperty()
      .addListener((observable, oldValue, newValue) ->
        showPersonDetails(newValue)
      );
  }

  /**
   * Вызывается главным приложением, которое даёт на себя ссылку
   *
   * @param mainApp
   */
  public void setMainApp(MainApp mainApp) {
    this.mainApp = mainApp;
    personTable.setItems(mainApp.getPersonData());
  }

  //Файл PersonOverviewController.java
  /*Метод заполняет все текстовые поля, отображая подробности об
адресате. Если указанный адресат = null, то все текстовые поля
очищаются.
 * @param person — адресат типа Person или null
 */
  private void showPersonDetails(Person person) {
    if (person != null) {
      // Заполняем метки информацией из объекта person.
      firstNameLabel.setText(person.getFirstName());
      lastNameLabel.setText(person.getLastName());
      streetLabel.setText(person.getStreet());

      postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
      cityLabel.setText(person.getCity());
      birthdayLabel.setText(DateUtil.format(person.getBirthday()));
    } else {
      // Если Person = null, то убираем весь текст.
      firstNameLabel.setText("");
      lastNameLabel.setText("");
      streetLabel.setText("");
      postalCodeLabel.setText("");
      cityLabel.setText("");
      birthdayLabel.setText("");
    }
  }

  //Пример №11. Поэтапное создание приложения с использованием библиотеки JavaFX
  //Файл PersonOverviewController.java
  /* Вызывается, когда пользователь кликает по кнопке удаления */
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

  //Пример №11. Поэтапное создание приложения с использованием библиотеки JavaFX
  //Файл PersonOverviewController.java

  /**
   * Вызывается, когда пользователь кликает по кнопке "Добавить"
   * Открывает диалоговое окно с дополнительной информацией
   * нового адресата
   */
  @FXML
  private void handleNewPerson() {
    Person person = new Person();
    boolean okClicked = mainApp.showPersonEditDialog(person);
    if (okClicked) mainApp.getPersonData().add(person);
  }

  /**
   * Вызывается, когда пользователь кликает по кнопка
   * "Редактировать"
   * Открывает диалоговое окно для изменения выбранного
   * адресата.
   */
  @FXML
  private void handleEditPersont() {
    Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
    if (selectedPerson != null) {
      boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
      if (okClicked) showPersonDetails(selectedPerson);
    } else {
      // Ничего не выбрано.
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.initOwner(mainApp.getPrimaryStage());
      alert.setTitle("Нет выбранной записи");
      alert.setHeaderText("Не выбрана запись");
      alert.setContentText("Выберите запись в таблице для редактирования");
      alert.showAndWait();
    }
  }
}
