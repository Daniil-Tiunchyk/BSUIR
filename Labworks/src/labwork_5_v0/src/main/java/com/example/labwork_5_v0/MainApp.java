package com.example.labwork_5_v0;

//Пример №11. Поэтапное создание приложения с использованием библиотеки JavaFX

import com.example.labwork_5_v0.controllers.PersonEditDialogController;
import com.example.labwork_5_v0.controllers.PersonOverviewController;
import com.example.labwork_5_v0.model.Person;
import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {

  private Stage primaryStage;
  private BorderPane rootLayout;
  private ObservableList<Person> personData = FXCollections.observableArrayList();

  @Override
  public void start(Stage stage) {
    this.primaryStage = stage;
    this.primaryStage.setTitle("Приложение AddressApp");
    this.primaryStage.getIcons()
      .add(new Image("file:resources/images/luffy.png"));
    initRootLayout();
    showPersonOverview();
  }

  public MainApp() {
    personData.add(new Person("Александра", "Мишкина"));
    personData.add(new Person("Иван", "Иванов"));
    personData.add(new Person("Петр", "Захаров"));
    personData.add(new Person("Даниил", "Петров"));
    personData.add(new Person("Светлана", "Захарова"));
    personData.add(new Person("Лидия", "Адамова"));
    personData.add(new Person("Анна", "Сергеева"));
    personData.add(new Person("Стефан", "Маховский"));
    personData.add(new Person("Мартин", "Петровский"));
  }

  /**
   * Возвращает данные в виде наблюдаемого списка адресатов.
   *
   * @return
   */
  public ObservableList<Person> getPersonData() {
    return personData;
  }

  /**
   * Инициализирует корневой макет.
   */
  private void initRootLayout() {
    try {
      // Загружаем корневой макет из fxml файла
      FXMLLoader loader = new FXMLLoader();

      loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
      rootLayout = (BorderPane) loader.load();
      // Отображаем сцену, содержащую корневой макет
      Scene scene = new Scene(rootLayout);
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Показывает в корневом макете сведения об адресатах
   */
  public void showPersonOverview() {
    try {
      // Загружаем сведения об адресатах
      FXMLLoader loader = new FXMLLoader();

      loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
      AnchorPane personOverview = (AnchorPane) loader.load();
      // Помещаем сведения об адресатах в центр корневого макета
      rootLayout.setCenter(personOverview);
      // создание экземпляра класса контроллера
      PersonOverviewController controller = loader.getController();
      // передача контроллеру ссылки на главное приложение
      controller.setMainApp(this);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //Пример №11. Поэтапное создание приложения с использованием библиотеки JavaFX
  //Файл MainApp.java

  /**
   * Открывает диалоговое окно для изменения деталей указанного
   * адресата.
   * Если пользователь кликнул OK, то изменения сохраняются в
   * предоставленном
   * объекте адресата и возвращается значение true.
   *
   * @param person - объект адресата, который надо изменить
   * @return true, если пользователь кликнул OK, в противном
   * случае false.
   */
  public boolean showPersonEditDialog(Person person) {
    // Загружаем fxml-файл и создаём новую сцену
    // для всплывающего диалогового окна.
    try {
      FXMLLoader loader = new FXMLLoader();

      loader.setLocation(
        MainApp.class.getResource("view/PersonEditDialog.fxml")
      );
      AnchorPane page = loader.load();
      // Создаём диалоговое окно Stage.
      Stage dialogStage = new Stage();
      dialogStage.setTitle("Редактирование");
      dialogStage.initModality(Modality.WINDOW_MODAL);
      dialogStage.initOwner(primaryStage);
      Scene scene = new Scene(page);
      dialogStage.setScene(scene);
      // Передаём адресата в контроллер.
      PersonEditDialogController controller = loader.getController();
      controller.setDialogStage(dialogStage);
      controller.setPerson(person);
      // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
      dialogStage.showAndWait();
      return controller.isOkClicked();
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Возвращает главную сцену.
   */
  public Stage getPrimaryStage() {
    return primaryStage;
  }

  public static void main(String[] args) {
    launch(args);
  }
}
