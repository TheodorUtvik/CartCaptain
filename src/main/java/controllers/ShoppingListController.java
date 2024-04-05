package controllers;

import static controllers.SceneUtils.changeScene;

import entities.FoodItem;
import file_handling.FileHandler;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.util.Iterator;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;


public class ShoppingListController implements Initializable {

  @FXML
  public ImageView homeButtonImage;

  @FXML
  public ImageView recipeButtonImage;

  @FXML
  public ImageView fridgeButtonImage;

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
  private Button searchButton;

  @FXML
  private Button backButton;
  @FXML
  private Text header;

  @FXML
  private Text header2;

  @FXML
  private ImageView hamburgerMenu;

  @FXML
  private ListView<String> foodItemsView;

  @FXML
  private ListView<String> shoppingListView;

  @FXML
  private Button homeButton;

  @FXML
  private Button recipeButton;

  @FXML
  private Button fridgeButton;
  @FXML
  private ListView<String> searchResultsListView;




  /**
   * Initializes the controller class. This method is automatically called after the fxml file has
   * been loaded.
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    homeButtonImage.setVisible(false); // Start with the home button invisible
    searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
      // If there is text, show the search results list view.
      if (!newValue.isEmpty()) {
        foodItemsView.setVisible(true);
        searchBarTextChanged(); // Update the list view with the filtered items
      } else {
        foodItemsView.setVisible(false); // Hide the list view when search bar is empty
      }
    });

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
        onSaveShoppingListButtonClicked();
        inputQuantityField.setVisible(false);
        inputQuantityField.clear();
      }
    });



    try {
      Iterator<FoodItem> foodItemsIterator = FileHandler.readFoodFromFile("src/main/resources/shoppingList.csv");
      while (foodItemsIterator.hasNext()) {
        FoodItem item = foodItemsIterator.next();
        shoppingListView.getItems().add(item.getDetails2());
      }
    } catch (Exception e) {
      System.err.println("Error initializing shopping list view: " + e.getMessage());
      e.printStackTrace();
    }
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

  private void searchBarTextChanged() {
    foodItemsView.getItems().clear();
    String searchQuery = searchBar.getText().toLowerCase();
    Iterator<FoodItem> allFoodItems = FileHandler.readFoodFromFile(
        "src/main/resources/foodItems.csv");
    while (allFoodItems.hasNext()) {
      FoodItem foodItem = allFoodItems.next();
      if (foodItem.getName().toLowerCase().contains(searchQuery)) {
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
  @FXML
  private void onAddButtonClicked() {
    String selectedItem = foodItemsView.getSelectionModel().getSelectedItem();
    if (selectedItem != null) {
      showInputFieldForQuantity(selectedItem);
    }
  }

  @FXML
  private void showInputFieldForQuantity(String selectedItem) {
    inputQuantityField.setVisible(true);
    inputQuantityField.requestFocus();
    inputQuantityField.setOnAction(event -> {
      int quantity;
      try {
        quantity = Integer.parseInt(inputQuantityField.getText());
      } catch (NumberFormatException e) {
        quantity = 1; // Default to 1 if input is not a number
        System.err.println("Invalid quantity, defaulting to 1.");
      }
      addItemWithQuantity(selectedItem, quantity);
    });
  }

  private void addItemWithQuantity(String item, int quantity) {
    String itemWithQuantity = String.format("%s - %d", item, quantity);
    if (!shoppingListView.getItems().contains(itemWithQuantity)) {
      shoppingListView.getItems().add(itemWithQuantity);
    }
  }


  private void addSelectedItemWithQuantity() {
    String selectedItem = foodItemsView.getSelectionModel().getSelectedItem();
    if (selectedItem == null) {
      return;
    }
    int quantity;
    try {
      quantity = Integer.parseInt(inputQuantityField.getText());
    } catch (NumberFormatException e) {
      System.err.println("Invalid quantity entered. Please enter a valid number.");
      return;
    }
    String itemWithQuantity = String.format("%s - %d", selectedItem, quantity);
    if (!shoppingListView.getItems().contains(itemWithQuantity)) {
      shoppingListView.getItems().add(itemWithQuantity);
      updateCsvWithItem(itemWithQuantity);
    }
  }


  private void updateCsvWithItem(String itemWithQuantity) {
    try {
      String filePath = "src/main/resources/shoppingList.csv";
      List<String> lines = Arrays.asList(itemWithQuantity);
      Files.write(Paths.get(filePath), lines, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    } catch (IOException e) {
      System.err.println("Failed to update the CSV file: " + e.getMessage());
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
  }

  @FXML
  private void onRemoveFromShoppingListButtonClicked() {
    String selectedItem = shoppingListView.getSelectionModel().getSelectedItem();
    if (selectedItem != null) {
      shoppingListView.getItems().remove(selectedItem);
      removeItemFromCsv(selectedItem);
    }
  }

  private void removeItemFromCsv(String item) {
    try {
      List<String> lines = FileHandler.readLinesFromFile("src/main/resources/shoppingList.csv");
      lines.remove(item);
      Files.write(Paths.get("src/main/resources/shoppingList.csv"), lines, StandardCharsets.UTF_8);
    } catch (IOException e) {
      System.err.println("Failed to remove item from CSV: " + e.getMessage());
    }
  }

  /**
   * This method will allow the user to clear the shopping list. It will be triggered by a button
   * click. It should clear the shopping list.
   */
  @FXML
  private void onClearShoppingListButtonClicked() {
    shoppingListView.getItems().clear();

  }

  /**
   * Toggles the visibility of the category buttons. If the buttons are visible, they will be hidden
   * and disabled.
   */
  public void showMenu(MouseEvent event) {
    if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
      boolean isMenuVisible = homeButton.isVisible();
      homeButton.setVisible(!isMenuVisible);
      fridgeButton.setVisible(!isMenuVisible);
      recipeButton.setVisible(!isMenuVisible);

      homeButtonImage.setVisible(!isMenuVisible);
      fridgeButtonImage.setVisible(!isMenuVisible);
      recipeButtonImage.setVisible(!isMenuVisible);

      homeButton.setDisable(isMenuVisible);
      fridgeButton.setDisable(isMenuVisible);
      recipeButton.setDisable(isMenuVisible);
    }
  }




  /**
   * Changes the scene to the front page.
   */
  @FXML
  public void goHome(ActionEvent event) {
    changeScene(event, "/scenebuilderjavafxapp/CartCaptainFrontPage.fxml", "Front Page");
  }

  /**
   * Changes the scene to the recipes page.
   */
  @FXML
  public void goRecipes(ActionEvent event) {
    changeScene(event, "/scenebuilderjavafxapp/RecipesFrontPage.fxml", "Recipe");
  }

  /**
   * Changes the scene to the fridge page.
   */
  @FXML
  public void goFridge(ActionEvent event) {
    changeScene(event, "/scenebuilderjavafxapp/FridgeFrontPage.fxml", "Fridge");
  }
}
