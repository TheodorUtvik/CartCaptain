package application;

import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class MainApp extends Application {

  @Override
  public void start(Stage primaryStage) {
    try {
      // Assuming the FXML file is placed under src/main/resources
      Parent root = FXMLLoader.load(
          Objects.requireNonNull(getClass().getResource(
              "/scenebuilderjavafxapp/CartCaptainFrontPage.fxml"))); // Replace "YourFXMLFileName.fxml" with the actual name of your FXML file

      Scene scene = new Scene(root);
      primaryStage.setTitle("Cart Captain"); // Set the window title here
      primaryStage.setScene(scene);
      primaryStage.show();

    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  public static void maino(String[] args) {
    launch(args);
  }
}
