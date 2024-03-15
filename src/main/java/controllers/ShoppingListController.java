package controllers;

import static controllers.SceneUtils.changeScene;

import java.awt.TextField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javax.swing.text.html.ListView;

public class ShoppingListController  {

  HashMap<String, ArrayList<String>> shoppingList = new HashMap<>();

  @FXML
  private void goBack(ActionEvent event) {
    changeScene(event, "/scenebuilderjavafxapp/CartCaptainFrontPage.fxml", "Front Page");
  }

  @FXML
  private TextField searchBar;

  @FXML
  private ListView<String> shoppingListView;

  @FXML
  void searchBar(ActionEvent event) {
    shoppingListView.getItems().clear();
    shoppingListView.getItems().addAll(shoppingList.get(searchBar.getText()));
  }

}
