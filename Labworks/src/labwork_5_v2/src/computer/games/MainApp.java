package computer.games;

import computer.games.model.Game;
import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

  private Stage primaryStage;
  private BorderPane rootLayout;
  private ObservableList<Game> gamesData = FXCollections.observableArrayList();

  @Override
  public void start(Stage stage) {
    this.primaryStage = stage;
    this.primaryStage.setTitle("Компьютерные игры");
    initRootLayout();
    showGamesOverview();
  }

  public MainApp() {
    gamesData.add(new Game("Baldur's Gate", 2023));
    gamesData.add(new Game("Screeps", 2016));
  }

  private void initRootLayout() {
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
      rootLayout = (BorderPane) loader.load();
      Scene scene = new Scene(rootLayout);
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void showGamesOverview() {
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(MainApp.class.getResource("view/GamesOverview.fxml"));
      AnchorPane gamesOverview = (AnchorPane) loader.load();
      rootLayout.setCenter(gamesOverview);
      GameOverviewController controller = loader.getController();
      controller.setMainApp(this);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Stage getPrimaryStage() {
    return primaryStage;
  }

  public static void main(String[] args) {
    launch(args);
  }
}
