package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Utility class for changing scenes in the application. The class contains a method for changing
 * scenes.
 *
 * @author Theodor Sjetnan Utvik
 * @version 0.0.1
 * @since 12.03.2024
 */


public class SceneUtils {

  public static void changeScene(ActionEvent event, String fxmlFile, String title) {
    try {
      FXMLLoader loader = new FXMLLoader(SceneUtils.class.getResource(fxmlFile));
      Parent parent = loader.load();

      Scene scene = new Scene(parent);
      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      stage.setScene(scene);
      stage.setTitle(title);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


}

