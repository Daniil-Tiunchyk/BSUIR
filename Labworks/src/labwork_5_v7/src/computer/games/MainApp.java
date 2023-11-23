package computer.games;

import computer.games.controllers.PersonEditDialogController;
import computer.games.controllers.PersonOverviewController;
import computer.games.model.MedicalPerson;
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
  private final ObservableList<MedicalPerson> medicalPersonData = FXCollections.observableArrayList();

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
    medicalPersonData.add(new MedicalPerson("Александра", "Мишкина"));
    medicalPersonData.add(new MedicalPerson("Иван", "Иванов"));
    medicalPersonData.add(new MedicalPerson("Петр", "Захаров"));
    medicalPersonData.add(new MedicalPerson("Даниил", "Петров"));
    medicalPersonData.add(new MedicalPerson("Светлана", "Захарова"));
    medicalPersonData.add(new MedicalPerson("Лидия", "Адамова"));
    medicalPersonData.add(new MedicalPerson("Анна", "Сергеева"));
    medicalPersonData.add(new MedicalPerson("Стефан", "Маховский"));
    medicalPersonData.add(new MedicalPerson("Мартин", "Петровский"));
  }

  public ObservableList<MedicalPerson> getPersonData() {
    return medicalPersonData;
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

  public boolean showPersonEditDialog(MedicalPerson medicalPerson) {
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
      controller.setPerson(medicalPerson);
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
