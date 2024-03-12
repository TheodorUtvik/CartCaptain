package controllers;

import static controllers.SceneUtils.changeScene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ShoppingListController {

  //@FXML
  //private button goBackButton;

  @FXML
  private void goBack(ActionEvent event) {
    changeScene(event, "/scenebuilderjavafxapp/CartCaptainFrontPage.fxml", "Front Page");
  }

}
