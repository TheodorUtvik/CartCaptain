package controllers;

import static controllers.SceneUtils.changeScene;

import entities.FoodItem;
import file_handling.FileHandler;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.TextField;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import userinterfacetools.UserInterfaceMethods;
import javafx.fxml.Initializable;


public class ShoppingListController implements Initializable {


  @FXML
  private void goBack(ActionEvent event) {
    changeScene(event, "/scenebuilderjavafxapp/CartCaptainFrontPage.fxml", "Front Page");
  }

  @FXML
  private TextField searchBar;

  @FXML
  private ListView<String> shoppingListView;

  /**
   * Initializes the controller class. This method is automatically called after the fxml file has
   * been loaded.
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
      searchBarTextChanged(); // This will be called every time the text in searchBar changes
    });
    // Any other initialization code can go here
  }

  /**
   * This method should be triggered by a listener on the TextField's textProperty to update the
   * ListView with the search results. It should be called every time the text in the TextField
   * changes.
   */
  @FXML
  private void onSearchButtonClicked() {
    shoppingListView.getItems().clear();
    searchBarTextChanged();
  }
  private void searchBarTextChanged() { // This method should be triggered by a listener on the TextField's textProperty
    String searchQuery = searchBar.getText().toLowerCase();
    Iterator<FoodItem> allFoodItems = FileHandler.readFoodFromFile(
        "src/main/resources/foodItems.csv"); // Adjust the path as needed
    while (allFoodItems.hasNext()) {
      FoodItem foodItem = allFoodItems.next();
      if (foodItem.getName().toLowerCase().contains(searchQuery)) {
        shoppingListView.getItems().add(foodItem.getName());
      }
    }
  }




}
