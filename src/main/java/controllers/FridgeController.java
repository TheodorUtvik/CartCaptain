package controllers;

import static controllers.SceneUtils.changeScene;

import entities.FoodItem;
import file_handling.FileHandler;
import java.io.File;
import java.util.Iterator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class FridgeController {

  @FXML
  private TextField searchField;

  @FXML
  private Button searchButton;

  @FXML
  private ImageView hamburgerMenu;

  @FXML
  private Button allCategories;

  @FXML
  private Button dairyCategory;

  @FXML
  private Button meatCategory;

  @FXML
  private Button fishCategory;

  @FXML
  private Button grainsCategory;

  @FXML
  private Button fruitAndVegetablesCategory;

  @FXML
  private ListView<String> fridgeListView;

  @FXML
  private Button homeButton;

  @FXML
  private Button recipeButton;

  @FXML
  private void initialize() { // This method should be triggered by a listener on the TextField's textProperty
    Iterator<FoodItem> allFoodItems = FileHandler.readFoodFromFile(
        "src/main/resources/foodItems.csv"); // Adjust the path as needed
    while (allFoodItems.hasNext()) {
      FoodItem foodItem = allFoodItems.next();
      fridgeListView.getItems().add(foodItem.getName());
    }
  }

  @FXML
  public void categoryAll(ActionEvent actionEvent) {
    fridgeListView.getItems().clear();
    Iterator<FoodItem> allFoodItems = FileHandler.readFoodFromFile(
        "src/main/resources/foodItems.csv");
    while (allFoodItems.hasNext()) {
      FoodItem foodItem = allFoodItems.next();
      fridgeListView.getItems().add(foodItem.getName());
    }
  }

  @FXML
  public void categoryDairy(ActionEvent actionEvent) {
    fridgeListView.getItems().clear();
    Iterator<FoodItem> allFoodItems = FileHandler.readFoodFromFile(
        "src/main/resources/foodItems.csv");
    while (allFoodItems.hasNext()) {
      FoodItem foodItem = allFoodItems.next();
      if (foodItem.getFoodType().equals("Meieri")) {
        fridgeListView.getItems().add(foodItem.getName());
      }
    }
  }

  @FXML
  public void categoryMeat(ActionEvent actionEvent) {
    fridgeListView.getItems().clear();
    Iterator<FoodItem> allFoodItems = FileHandler.readFoodFromFile(
        "src/main/resources/foodItems.csv");
    while (allFoodItems.hasNext()) {
      FoodItem foodItem = allFoodItems.next();
      if (foodItem.getFoodType().equals("Kjøtt")) {
        fridgeListView.getItems().add(foodItem.getName());
      }
    }
  }

  public void categoryFish(ActionEvent actionEvent) {
    fridgeListView.getItems().clear();
    Iterator<FoodItem> allFoodItems = FileHandler.readFoodFromFile(
        "src/main/resources/foodItems.csv");
    while (allFoodItems.hasNext()) {
      FoodItem foodItem = allFoodItems.next();
      if (foodItem.getFoodType().equals("Fisk")) {
        fridgeListView.getItems().add(foodItem.getName());
      }
    }
  }

  @FXML
  public void categoryGrains(ActionEvent actionEvent) {
    fridgeListView.getItems().clear();
    Iterator<FoodItem> allFoodItems = FileHandler.readFoodFromFile(
        "src/main/resources/foodItems.csv");
    while (allFoodItems.hasNext()) {
      FoodItem foodItem = allFoodItems.next();
      if (foodItem.getFoodType().equals("Korn")) {
        fridgeListView.getItems().add(foodItem.getName());
      }
    }
  }

  @FXML
  public void categoryFruitAndVegetables(ActionEvent actionEvent) {
    fridgeListView.getItems().clear();
    Iterator<FoodItem> allFoodItems = FileHandler.readFoodFromFile(
        "src/main/resources/foodItems.csv");
    while (allFoodItems.hasNext()) {
      FoodItem foodItem = allFoodItems.next();
      if (foodItem.getFoodType().equals("Frukt & Grønt")) {
        fridgeListView.getItems().add(foodItem.getName());
      }
    }
  }

  @FXML
  public void showMenu(MouseEvent event) {
    if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
      if (homeButton.isVisible()) {
        // If buttons are visible, hide them and disable them
        homeButton.setVisible(false);
        recipeButton.setVisible(false);
        homeButton.setDisable(true);
        recipeButton.setDisable(true);
      } else {
        // If buttons are not visible, show them and enable them
        homeButton.setVisible(true);
        recipeButton.setVisible(true);
        homeButton.setDisable(false);
        recipeButton.setDisable(false);
      }
    }
  }


  @FXML
  public void goHome(ActionEvent event) {
    changeScene(event, "/scenebuilderjavafxapp/CartCaptainFrontPage.fxml", "Front Page");
  }

  @FXML
  public void goRecipes(ActionEvent event) {
    changeScene(event, "/scenebuilderjavafxapp/RecipesFrontPage.fxml", "Recipe");
  }
}
