package computer.games;

import computer.games.controllers.PersonEditDialogController;
import computer.games.controllers.PersonOverviewController;
import computer.games.model.Correspondence;

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
  private final ObservableList<Correspondence> correspondenceData = FXCollections.observableArrayList();

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
    correspondenceData.add(new Correspondence("Второй том мёртвых душ", "Иван Мишкин"));
    correspondenceData.add(new Correspondence("Война и мир", "Лев Толстой"));
    correspondenceData.add(new Correspondence("Преступление и наказание", "Фёдор Достоевский"));
    correspondenceData.add(new Correspondence("Отцы и дети", "Иван Тургенев"));
    correspondenceData.add(new Correspondence("Анна Каренина", "Лев Толстой"));
    correspondenceData.add(new Correspondence("Собачье сердце", "Михаил Булгаков"));
    correspondenceData.add(new Correspondence("Мастер и Маргарита", "Михаил Булгаков"));
    correspondenceData.add(new Correspondence("Идиот", "Фёдор Достоевский"));
    correspondenceData.add(new Correspondence("Мёртвые души", "Николай Гоголь"));
    correspondenceData.add(new Correspondence("Евгений Онегин", "Александр Пушкин"));
    correspondenceData.add(new Correspondence("Чёрный обелиск", "Эрих Мария Ремарк"));
  }

  public ObservableList<Correspondence> getPersonData() {
    return correspondenceData;
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

      loader.setLocation(MainApp.class.getResource("view/CorrespondenceOverview.fxml"));
      AnchorPane personOverview = loader.load();
      rootLayout.setCenter(personOverview);
      PersonOverviewController controller = loader.getController();
      controller.setMainApp(this);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public boolean showPersonEditDialog(Correspondence correspondence) {
    try {
      FXMLLoader loader = new FXMLLoader();

      loader.setLocation(
        MainApp.class.getResource("view/CorrespondenceEditDialog.fxml")
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
      controller.setPerson(correspondence);
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
