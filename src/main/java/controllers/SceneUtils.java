package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


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

  /**
   * Changes the scene to the given FXML file. Used for changing scenes from within a controller.
   * The scene is changed by clikcing an ImageView element.
   * @param event The event that triggers the scene change.
   */

  public static void changeSceneWithImage(MouseEvent event, String fxmlFile, String title) {
    try {
      FXMLLoader loader = new FXMLLoader(SceneUtils.class.getResource(fxmlFile));
      Parent root = loader.load();

      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.setTitle(title);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

