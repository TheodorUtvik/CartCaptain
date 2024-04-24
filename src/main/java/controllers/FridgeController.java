package controllers;

import static controllers.SceneUtils.changeScene;

import entities.FoodItem;
import file_handling.FileHandler;
import java.util.Iterator;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FridgeController is the controller for the fridge page in the application. It handles the logic
 * for displaying food items in the fridge, searching for food items, and categorizing food items.
 * It also provides functionality for navigating to other pages in the application.
 *
 * @author Sigurd Riseth, Theodor Sjetnan Utvik
 * @version 0.0.2
 * @see FoodItem
 * @see FileHandler
 * @since 05.03.2024
 */

public class FridgeController {

  /**
   * The search field for searching for food items in the fridge.
   */
  @FXML
  private TextField searchField;

  /**
   * The text that displays an error message if the user tries to change the amount of a food item
   * without selecting one.
   */
  @FXML
  private Text itemError;

  private TextField nameField;

  private ListView<String> foodTypeList;

  /**
   * The ListView that displays all food items in the fridge.
   */
  @FXML
  private ListView<String> fridgeListView;

  /**
   * The button for navigating to the home page.
   */
  @FXML
  private Button homeButton;

  /**
   * The button for navigating to the recipe page.
   */
  @FXML
  private Button recipeButton;

  /**
   * The button for navigating to the shopping list page.
   */
  @FXML
  private Button shoppingListButton;

  /**
   * The image for the home button.
   */
  @FXML
  public ImageView homeButtonImage;

  /**
   * The image for the recipe button.
   */
  @FXML
  public ImageView recipeButtonImage;

  /**
   * The image for the shopping list button.
   */
  @FXML
  public ImageView shoppingListButtonImage;

  /**
   * The button for adding a new food item to the fridge.
   */
  @FXML
  private Button addItem;

  /**
   * Displays the food item in the following format: "name - quantity unit".
   *
   * @param foodItem the food item to display
   * @return a string representation of the food item
   */
  private String foodItemDisplay(FoodItem foodItem) {
    return foodItem.getName() + " - " + foodItem.getQuantity() + " " + foodItem.getUnit();
  }

  /**
   * Opens a dialog for adding a new food item to the fridge.
   */
  @FXML
  public void addItem() {
    Stage addStage = new Stage();
    BorderPane pane = new BorderPane();

    VBox box = new VBox();
    box.setAlignment(Pos.CENTER);
    box.setSpacing(10);

    TextField nameField = new TextField();
    nameField.promptTextProperty().setValue("Vare...");
    nameField.setMaxWidth(200);

    Text quantityText = new Text();
    quantityText.setVisible(false);

    foodTypeList = new ListView<>();
    initList();
    foodTypeList.setMaxHeight(100);
    foodTypeList.setMaxWidth(200);
    foodTypeList.setVisible(false);

    nameField.textProperty().addListener((observable, oldValue, newValue) -> {
      foodTypeList.setVisible(true);
      searchBarTextChanged(nameField.getText().toLowerCase());
    });

    VBox foodTypeBox = new VBox();
    foodTypeBox.setAlignment(Pos.CENTER);
    foodTypeBox.getChildren().addAll(nameField, foodTypeList);

    TextField quantityField = new TextField();
    quantityField.promptTextProperty().setValue("Mengde...");
    quantityField.setMaxWidth(200);

    Text errorText = new Text();
    errorText.setVisible(false);
    errorText.setStyle("-fx-fill: red");

    HBox saveButtons = new HBox();
    saveButtons.setAlignment(Pos.CENTER);
    saveButtons.setSpacing(10);
    Button saveButton = new Button("Lagre");
    Button cancelButton = new Button("Lukk");
    saveButtons.getChildren().addAll(cancelButton, saveButton);

    foodTypeList.setOnMouseClicked(event -> {
      String selectedItem = foodTypeList.getSelectionModel().getSelectedItem();
      nameField.setText(selectedItem);
      foodTypeList.setVisible(false);
      quantityText.setText(getQuantityField(selectedItem));
      quantityText.setVisible(true);
    });
    cancelButton.setOnAction(event -> addStage.close());
    saveButton.setOnAction(event -> {
      try {
        String name = nameField.getText();
        String quantity = quantityField.getText();
        if (name.isBlank() || quantity.isBlank()) {
          throw new IllegalArgumentException("Empty fields.");
        }
        double quantityDouble = Double.parseDouble(quantity);
        FoodItem foodItem = null;
        Iterator<FoodItem> allFoodItems = FileHandler.readFoodFromFile(
            "src/main/resources/foodItems.csv");
        for (Iterator<FoodItem> it = allFoodItems; it.hasNext(); ) {
          FoodItem foodItems = it.next();
          if (foodItems.getName().equals(name)) {
            foodItem = foodItems;
            foodItem.setQuantity(quantityDouble);
          }
        }
        if (foodItem == null) {
          throw new IllegalArgumentException("Item not found.");
        }
        FileHandler.addFoodItem("src/main/resources/Fridge.csv", foodItem);
        updateFridgeListView();
        addStage.close();
      } catch (Exception e) {
        errorText.setText("En feil oppsto. Vennligst fyll ut alle feltene.");
        errorText.setVisible(true);
      }
    });

    box.getChildren().addAll(foodTypeBox, quantityField, quantityText, saveButtons, errorText);
    pane.setCenter(box);

    Scene scene = new Scene(pane, 400, 300);
    addStage.setScene(scene);
    addStage.setTitle("Legg til vare");
    addStage.show();
  }

  private String getQuantityField(String selectedItem) {
    Iterator<FoodItem> allFoodItems = FileHandler.readFoodFromFile("src/main/resources/foodItems.csv");
    while (allFoodItems.hasNext()) {
      FoodItem foodItem = allFoodItems.next();
      if (foodItem.getName().equals(selectedItem)) {
        return foodItem.getUnit();
      }
    }
    return "";
  }

  /**
   * Initializes the list of food items that can be added to the fridge.
   */
  private void initList() {
    Iterator<FoodItem> allFoodItems = FileHandler.readFoodFromFile("src/main/resources/foodItems.csv");
    while (allFoodItems.hasNext()) {
      FoodItem foodItem = allFoodItems.next();
      foodTypeList.getItems().add(foodItem.getName());
    }
  }

  /**
   * This method will filter the food items based on the search query in the search bar. It will be
   * triggered by a listener on the search bar text property. It should update the foodItemsView
   * with the filtered items. The food items should be read from the file "foodItems.csv". The
   * search should be case-insensitive.
   */
  private void searchBarTextChanged(String searchQuery) {
    foodTypeList.getItems().clear();
    Iterator<FoodItem> allFoodItems = FileHandler.readFoodFromFile("src/main/resources/foodItems.csv");
    while (allFoodItems.hasNext()) {
      FoodItem foodItem = allFoodItems.next();
      if (foodItem.getName().toLowerCase().contains(searchQuery)) {
        foodTypeList.getItems().add(foodItem.getName());
      }
    }
  }

  /**
   * Reads the selected items and opens a menu for editing it using
   * <code>showAmountChangeDialog()</code>.
   *
   * @param event the event that triggers the method
   */
  @FXML
  public void changeAmount(ActionEvent event) {
    String selectedItem = fridgeListView.getSelectionModel().getSelectedItem();
    if (selectedItem == null) {
      itemError.setStyle("-fx-fill: red");
      itemError.setVisible(true);
      return;
    }
    String[] itemDetails = selectedItem.split(" - ");
    String itemName = itemDetails[0];
    String[] itemQuantity = itemDetails[1].split(" ");
    showAmountChangeDialog(itemName, itemQuantity);
  }

  /**
   * Opens a dialog for changing the amount of a food item in the fridge. Also allows the user to
   * remove the food item from the fridge.
   *
   * @param itemName     the name of the food item
   * @param itemQuantity the initial quantity of the food item
   */
  private void showAmountChangeDialog(String itemName, String[] itemQuantity) {
    Stage amountStage = new Stage();
    BorderPane pane = new BorderPane();

    VBox box = new VBox();
    box.setAlignment(Pos.CENTER);
    box.setSpacing(10);

    TextField amountField = new TextField();
    amountField.promptTextProperty().setValue("Ny mengde...");
    amountField.setMaxWidth(200);

    Text amountText = new Text(
        "Her kan du endre mengden " + itemName + " fra " + itemQuantity[0] + " " + itemQuantity[1]);
    Text errorText = new Text();
    errorText.setVisible(false);
    errorText.setStyle("-fx-fill: red");

    HBox saveButtons = new HBox();
    saveButtons.setAlignment(Pos.CENTER);
    saveButtons.setSpacing(10);
    Button saveButton = new Button("Lagre");
    Button cancelButton = new Button("Lukk");
    Button removeButton = new Button("Slett vare");
    saveButtons.getChildren().addAll(cancelButton, saveButton, removeButton);

    cancelButton.setOnAction(event -> amountStage.close());
    saveButton.setOnAction(event -> {
      try {
        String newAmount = amountField.getText();
        if (newAmountFaulty(newAmount)) {
          throw new NumberFormatException("Invalid number format.");
        }
        FileHandler.changeFoodItemQuantity("src/main/resources/Fridge.csv", itemName,
            newAmount);
        updateFridgeListView();
      } catch (Exception e) {
        errorText.setText("En feil oppsto. Vennligst skriv inn et gyldig tall.");
        errorText.setVisible(true);
      }
    });
    removeButton.setOnAction(event -> {
      try {
        FileHandler.removeFoodItem("src/main/resources/Fridge.csv", itemName);
        updateFridgeListView();
        amountStage.close();
      } catch (Exception e) {
        errorText.setText("Kunne ikke slette " + itemName + ".");
        errorText.setVisible(true);
      }
    });

    box.getChildren().addAll(amountText, amountField, saveButtons, errorText);
    pane.setCenter(box);

    Scene scene = new Scene(pane, 300, 200);
    amountStage.setScene(scene);
    amountStage.setTitle("Endre antall " + itemName);
    amountStage.show();
  }

  /**
   * Checks if a string is parseable to a double.
   *
   * @param newAmount the string to check
   * @return true if the string is not parseable to a double, false otherwise
   */
  private boolean newAmountFaulty(String newAmount) {
    try {
      Double.parseDouble(newAmount);
    } catch (NumberFormatException e) {
      return true;
    }
    return false;
  }

  /**
   * Updates the ListView with all food items in the fridge.
   */
  private void updateFridgeListView() {
    fridgeListView.getItems().clear();
    Iterator<FoodItem> allFoodItems = FileHandler.readFoodFromFile(
        "src/main/resources/Fridge.csv");
    while (allFoodItems.hasNext()) {
      FoodItem foodItem = allFoodItems.next();
      fridgeListView.getItems().add(foodItemDisplay(foodItem));
    }
  }

  /**
   * Init method that runs when the FXML file is loaded. It populates the ListView with all food
   * items in the fridge.
   */
  @FXML
  private void initialize() { // This method should be triggered by a listener on the TextField's textProperty
    itemError.setVisible(false);
    Iterator<FoodItem> allFoodItems = FileHandler.readFoodFromFile(
        "src/main/resources/Fridge.csv");
    while (allFoodItems.hasNext()) {
      FoodItem foodItem = allFoodItems.next();
      fridgeListView.getItems().add(foodItemDisplay(foodItem));
    }
  }

  /**
   * Displays all food items in the fridge.
   */
  @FXML
  public void categoryAll(ActionEvent actionEvent) {
    fridgeListView.getItems().clear();
    Iterator<FoodItem> allFoodItems = FileHandler.readFoodFromFile(
        "src/main/resources/Fridge.csv");
    while (allFoodItems.hasNext()) {
      FoodItem foodItem = allFoodItems.next();
      fridgeListView.getItems().add(foodItemDisplay(foodItem));
    }
  }

  /**
   * Displays all food items in the fridge that are categorized as dairy.
   */
  @FXML
  public void categoryDairy(ActionEvent actionEvent) {
    fridgeListView.getItems().clear();
    Iterator<FoodItem> allFoodItems = FileHandler.readFoodFromFile(
        "src/main/resources/Fridge.csv");
    while (allFoodItems.hasNext()) {
      FoodItem foodItem = allFoodItems.next();
      if (foodItem.getFoodType().equals("Meieri")) {
        fridgeListView.getItems().add(foodItemDisplay(foodItem));
      }
    }
  }

  /**
   * Displays all food items in the fridge that are categorized as meat.
   */
  @FXML
  public void categoryMeat(ActionEvent actionEvent) {
    fridgeListView.getItems().clear();
    Iterator<FoodItem> allFoodItems = FileHandler.readFoodFromFile(
        "src/main/resources/Fridge.csv");
    while (allFoodItems.hasNext()) {
      FoodItem foodItem = allFoodItems.next();
      if (foodItem.getFoodType().equals("Kjøtt")) {
        fridgeListView.getItems().add(foodItemDisplay(foodItem));
      }
    }
  }

  /**
   * Displays all food items in the fridge that are categorized as fish.
   */
  @FXML
  public void categoryFish(ActionEvent actionEvent) {
    fridgeListView.getItems().clear();
    Iterator<FoodItem> allFoodItems = FileHandler.readFoodFromFile(
        "src/main/resources/Fridge.csv");
    while (allFoodItems.hasNext()) {
      FoodItem foodItem = allFoodItems.next();
      if (foodItem.getFoodType().equals("Fisk")) {
        fridgeListView.getItems().add(foodItemDisplay(foodItem));
      }
    }
  }

  /**
   * Displays all food items in the fridge that are categorized as grains.
   */
  @FXML
  public void categoryGrains(ActionEvent actionEvent) {
    fridgeListView.getItems().clear();
    Iterator<FoodItem> allFoodItems = FileHandler.readFoodFromFile(
        "src/main/resources/Fridge.csv");
    while (allFoodItems.hasNext()) {
      FoodItem foodItem = allFoodItems.next();
      if (foodItem.getFoodType().equals("Korn")) {
        fridgeListView.getItems().add(foodItemDisplay(foodItem));
      }
    }
  }

  /**
   * Displays all food items in the fridge that are categorized as fruit and vegetables.
   */
  @FXML
  public void categoryFruitAndVegetables(ActionEvent actionEvent) {
    fridgeListView.getItems().clear();
    Iterator<FoodItem> allFoodItems = FileHandler.readFoodFromFile(
        "src/main/resources/Fridge.csv");
    while (allFoodItems.hasNext()) {
      FoodItem foodItem = allFoodItems.next();
      if (foodItem.getFoodType().equals("Frukt & Grønt")) {
        fridgeListView.getItems().add(foodItemDisplay(foodItem));
      }
    }
  }

  /**
   * Displays all food items in the fridge that contain the search query given in the searchField.
   */
  @FXML
  private void onSearchButtonClicked() {
    fridgeListView.getItems().clear();
    String searchQuery = searchField.getText().toLowerCase();
    Iterator<FoodItem> allFoodItems = FileHandler.readFoodFromFile(
        "src/main/resources/Fridge.csv");
    while (allFoodItems.hasNext()) {
      FoodItem foodItem = allFoodItems.next();
      if (foodItem.getName().toLowerCase().contains(searchQuery)) {
        fridgeListView.getItems().add(foodItemDisplay(foodItem));
      }
    }
  }

  /**
   * Displays the hamburger menu when the user clicks on the hamburger icon.
   */
  public void showMenu(MouseEvent event) {
    if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
      boolean isMenuVisible = homeButton.isVisible();
      homeButton.setVisible(!isMenuVisible);
      shoppingListButton.setVisible(!isMenuVisible);
      recipeButton.setVisible(!isMenuVisible);

      homeButtonImage.setVisible(!isMenuVisible);
      shoppingListButtonImage.setVisible(!isMenuVisible);
      recipeButtonImage.setVisible(!isMenuVisible);

      homeButton.setDisable(isMenuVisible);
      shoppingListButton.setDisable(isMenuVisible);
      recipeButton.setDisable(isMenuVisible);
    }
  }

  /**
   * Changes the scene to the front page.
   */
  @FXML
  public void goHome(ActionEvent event) {
    changeScene(event, "/scenebuilderjavafxapp/FrontPage.fxml", "Front Page");
  }

  /**
   * Changes the scene to the recipes page.
   */
  @FXML
  public void goRecipes(ActionEvent event) {
    changeScene(event, "/scenebuilderjavafxapp/Recipe.fxml", "Recipe");
  }

  /**
   * Changes the scene to the shopping list page.
   */
  @FXML
  public void goShoppingList(ActionEvent event) {
    changeScene(event, "/scenebuilderjavafxapp/ShoppingList.fxml", "Shopping List");
  }
}
