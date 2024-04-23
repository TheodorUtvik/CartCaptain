package application;

import java.util.Objects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main app class which inlcudes the main <code>start</code> method to run the application.
 */
public class MainApp extends Application {

  @Override
  public void start(Stage primaryStage) {
    try {
      Parent root = FXMLLoader.load(
          Objects.requireNonNull(getClass().getResource(
              "/scenebuilderjavafxapp/FrontPage.fxml")));

      Scene scene = new Scene(root);
      primaryStage.setTitle("Cart Captain");
      primaryStage.setScene(scene);
      primaryStage.show();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void maino(String[] args) {
    launch(args);
  }
}
