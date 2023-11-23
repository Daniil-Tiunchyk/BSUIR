package computer.games;

import computer.games.controllers.PersonEditDialogController;
import computer.games.controllers.PersonOverviewController;
import computer.games.model.Person;
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
  private final ObservableList<Person> personData = FXCollections.observableArrayList();

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

  public ObservableList<Person> getPersonData() {
    return personData;
  }

  private void initRootLayout() {
    try {
      FXMLLoader loader = new FXMLLoader();

      loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
      rootLayout = loader.load();
      Scene scene = new Scene(rootLayout);
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void showPersonOverview() {
    try {
      FXMLLoader loader = new FXMLLoader();

      loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
      AnchorPane personOverview = loader.load();
      rootLayout.setCenter(personOverview);
      PersonOverviewController controller = loader.getController();
      controller.setMainApp(this);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public boolean showPersonEditDialog(Person person) {
    try {
      FXMLLoader loader = new FXMLLoader();

      loader.setLocation(
        MainApp.class.getResource("view/PersonEditDialog.fxml")
      );
      AnchorPane page = loader.load();
      Stage dialogStage = new Stage();
      dialogStage.setTitle("Редактирование");
      dialogStage.initModality(Modality.WINDOW_MODAL);
      dialogStage.initOwner(primaryStage);
      Scene scene = new Scene(page);
      dialogStage.setScene(scene);
      PersonEditDialogController controller = loader.getController();
      controller.setDialogStage(dialogStage);
      controller.setPerson(person);
      dialogStage.showAndWait();
      return controller.isOkClicked();
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }

  public Stage getPrimaryStage() {
    return primaryStage;
  }

  public static void main(String[] args) {
    launch(args);
  }
}
