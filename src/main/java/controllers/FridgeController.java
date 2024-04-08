package controllers;

import static controllers.SceneUtils.changeScene;

import entities.FoodItem;
import file_handling.FileHandler;
import java.util.Iterator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FridgeController is the controller for the fridge page in the application. It handles the logic for
 * displaying food items in the fridge, searching for food items, and categorizing food items.
 * It also provides functionality for navigating to other pages in the application.
 *
 * @see FoodItem
 * @see FileHandler
 * @since 05.03.2024
 * @version 0.0.2
 * @author Sigurd Riseth, Theodor Sjetnan Utvik
 */

public class FridgeController {

  @FXML
  private TextField searchField;

  @FXML
  private ListView<String> fridgeListView;

  @FXML
  private Button homeButton;

  @FXML
  private Button recipeButton;
  @FXML
  private Button shoppingListButton;

  public ImageView homeButtonImage;

  @FXML
  public ImageView recipeButtonImage;

  @FXML
  public ImageView shoppingListButtonImage;

  private String foodItemDisplay(FoodItem foodItem) {
    return foodItem.getName() + " - " + foodItem.getQuantity() + " " + foodItem.getUnit();
  }

  /**
   * Init method that runs when the FXML file is loaded.
   * It populates the ListView with all food items in the fridge.
   */
  @FXML
  private void initialize() { // This method should be triggered by a listener on the TextField's textProperty
    Iterator<FoodItem> allFoodItems = FileHandler.readFoodFromFile(
        "src/main/resources/foodItems.csv"); // Adjust the path as needed
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
        "src/main/resources/foodItems.csv");
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
        "src/main/resources/foodItems.csv");
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
        "src/main/resources/foodItems.csv");
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
        "src/main/resources/foodItems.csv");
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
        "src/main/resources/foodItems.csv");
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
        "src/main/resources/foodItems.csv");
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
        "src/main/resources/foodItems.csv"); // Adjust the path as needed
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
    changeScene(event, "/scenebuilderjavafxapp/CartCaptainFrontPage.fxml", "Front Page");
  }

  /**
   * Changes the scene to the recipes page.
   */
  @FXML
  public void goRecipes(ActionEvent event) {
    changeScene(event, "/scenebuilderjavafxapp/recipePage.fxml", "Recipe");
  }

  /**
   * Changes the scene to the shopping list page.
   */
  @FXML
  public void goShoppingList(ActionEvent event) {
    changeScene(event, "/scenebuilderjavafxapp/ShoppingListFrontPage.fxml", "Shopping List");
  }
}
