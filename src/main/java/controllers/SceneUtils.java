package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
}
