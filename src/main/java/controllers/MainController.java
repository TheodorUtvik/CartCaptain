package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController {

  @FXML
  private Button shoppingListButton;

  @FXML
  private Button fridgeButton;

  @FXML
  private Button recipesButton;

  @FXML
  private void onShoppingList() {
    // Handle the shopping list button action here
    System.out.println("Shopping List button clicked");
  }

  @FXML
  private void onFridge() {
    // Handle the fridge button action here
    System.out.println("Fridge button clicked");
  }

  @FXML
  private void onRecipes() {
    // Handle the recipes button action here
    System.out.println("Recipes button clicked");
  }

}
