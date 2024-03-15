package controllers;

import static controllers.SceneUtils.changeScene;

import entities.FoodItem;
import file_handling.FileHandler;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
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
  public Button addButton;

  @FXML
  public Button removeButton;

  @FXML
  private void goBack(ActionEvent event) {
    changeScene(event, "/scenebuilderjavafxapp/CartCaptainFrontPage.fxml", "Front Page");
  }

  @FXML
  private TextField searchBar;

  @FXML
  private ListView<String> foodItemsView;

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
    foodItemsView.setOnMouseClicked(event -> {
      if (event.getClickCount() == 2) {
        String selectedItem = foodItemsView.getSelectionModel().getSelectedItem();
        if (selectedItem != null && !shoppingListView.getItems().contains(selectedItem)) {
          shoppingListView.getItems().add(selectedItem);
        }
      }
    });
  }

  /**
   * This method should be triggered by a listener on the TextField's textProperty to update the
   * ListView with the search results. It should be called every time the text in the TextField
   * changes.
   */
  @FXML
  private void onSearchButtonClicked() {
    foodItemsView.getItems().clear();
    searchBarTextChanged();
  }
  private void searchBarTextChanged() { // This method should be triggered by a listener on the TextField's textProperty
    foodItemsView.getItems().clear();
    String searchQuery = searchBar.getText().toLowerCase();
    Iterator<FoodItem> allFoodItems = FileHandler.readFoodFromFile(
        "src/main/resources/foodItems.csv"); // Adjust the path as needed
    while (allFoodItems.hasNext()) {
      FoodItem foodItem = allFoodItems.next();
      if (foodItem.getName().toLowerCase().contains(searchQuery)){
        foodItemsView.getItems().add(foodItem.getName());
      }
    }
  }

  /**
   * This method will allow the user to add items to the shopping list. It will be triggered by a
   * button click. It should add the selected item to the shopping list, which is a new list of
   * strings. The shopping list should be displayed in a ListView. The user should be able to add
   * multiple items to the shopping list.
   */
  @FXML
  private void onAddToShoppingListButtonClicked() {
    String selectedItem = foodItemsView.getSelectionModel().getSelectedItem();
    if (selectedItem != null && !shoppingListView.getItems().contains(selectedItem)) {
      shoppingListView.getItems().add(selectedItem);
    }
  }

  /**
   * This method will allow the user to remove items from the shopping list. It will be triggered by a
   * button click. It should remove the selected item from the shopping list.
   */
  @FXML
  private void onRemoveFromShoppingListButtonClicked() {
    String selectedItem = shoppingListView.getSelectionModel().getSelectedItem();
    if (selectedItem != null) {
      shoppingListView.getItems().remove(selectedItem);
    }
  }


}
