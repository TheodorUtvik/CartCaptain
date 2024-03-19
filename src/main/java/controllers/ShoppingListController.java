package controllers;

import static controllers.SceneUtils.changeScene;

import entities.FoodItem;
import file_handling.FileHandler;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.util.Iterator;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;


public class ShoppingListController implements Initializable {


  @FXML
  public Button addButton;

  @FXML
  public Button removeButton;

  @FXML
  public Button listToFileButton;

  @FXML
  public Button clearShoppingListButton;

  @FXML
  public TextField inputQuantityField;

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
    inputQuantityField.setOnKeyPressed(event -> {
      if (event.getCode() == KeyCode.ENTER) {
        addSelectedItemWithQuantity();
        inputQuantityField.setVisible(false);
        inputQuantityField.clear();
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
   * multiple items to the shopping list. When a user selects an item from the foodItemsView, the
   * user should decide the quantity of the item to add to the shopping list.
   */
  private void addSelectedItemWithQuantity() {
    String selectedItem = foodItemsView.getSelectionModel().getSelectedItem();
    if (selectedItem == null) return;

    int quantity = 1; // Default quantity
    if (!inputQuantityField.getText().isEmpty()) {
      try {
        quantity = Math.max(1, Integer.parseInt(inputQuantityField.getText()));
      } catch (NumberFormatException e) {
        System.err.println("Invalid quantity, defaulting to 1.");
      }
    }
    String itemWithQuantity = String.format("%s - %d", selectedItem, quantity);
    if (!shoppingListView.getItems().contains(itemWithQuantity)) {
      shoppingListView.getItems().add(itemWithQuantity);
    }
  }

  @FXML
  private void showInputFieldForQuantity() {
    inputQuantityField.setVisible(true);
    inputQuantityField.requestFocus(); // Focus on the TextField to immediately start typing
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

  /**
   * This method will allow the user to save the shopping list to a file. It will be triggered by a
   * button click. It should save the shopping list to a file with the name "shoppingList.csv". Each
   * item in the shopping list should be on a new line in the file.
   */
  @FXML
  private void onSaveShoppingListButtonClicked() {
    List<String> shoppingList = new ArrayList<>(shoppingListView.getItems());
    FileHandler.writeShoppingListToFile("src/main/resources/shoppingList.csv", shoppingList);
    shoppingListView.getItems().clear();
  }

  /**
   * This method will allow the user to clear the shopping list. It will be triggered by a button
   * click. It should clear the shopping list.
   */
  @FXML
  private void onClearShoppingListButtonClicked() {
    shoppingListView.getItems().clear();

  }
}
