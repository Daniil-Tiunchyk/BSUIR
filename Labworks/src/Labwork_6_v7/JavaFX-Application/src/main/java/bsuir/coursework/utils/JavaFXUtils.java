package bsuir.coursework.utils;

import bsuir.coursework.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class JavaFXUtils {

    public static void changeScene(String name, String title, Node sourceNode) {
        Stage stage = (Stage) sourceNode.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(name));

        try {
            Scene scene = new Scene(fxmlLoader.load());
            setStageProperties(stage, title, scene);
        } catch (IOException e) {
            System.err.println("Error loading the scene: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error changing the scene: " + e.getMessage());
        }
    }

    private static void setStageProperties(Stage stage, String title, Scene scene) {
        stage.setResizable(false);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    public static void handeNewStage(String name, String title) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource(name));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void handleClose(Button button) {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }

    public static void showWarningAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}