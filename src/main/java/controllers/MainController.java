package controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Controller class for the main page of the application. This class handles the event handlers for
 * the buttons on the main page. The buttons are for navigating between the shopping list, fridge,
 * and recipes pages.
 *
 * @author Theodor Sjetnan Utvik
 * @version 0.0.2
 * @since 12.03.2024
 */

public class MainController {

  @FXML
  private Button shoppingListButton;

  @FXML
  private Button fridgeButton;

  @FXML
  private Button recipesButton;

  @FXML
  private void onShoppingList() {
    try {
      FXMLLoader loader = new FXMLLoader(
          getClass().getResource("/scenebuilderjavafxapp/ShoppingList.fxml"));
      Parent shoppingListRoot = loader.load();

      Stage stage = (Stage) shoppingListButton.getScene().getWindow();
      Scene scene = new Scene(shoppingListRoot);
      stage.setScene(scene);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  private void onFridge() {
    try {
      FXMLLoader loader = new FXMLLoader(
          getClass().getResource("/scenebuilderjavafxapp/Fridge.fxml"));
      Parent fridgeRoot = loader.load();

      Stage stage = (Stage) fridgeButton.getScene().getWindow();
      Scene scene = new Scene(fridgeRoot);
      stage.setScene(scene);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  private void onRecipes() {
    try {
      FXMLLoader loader = new FXMLLoader(
          getClass().getResource("/scenebuilderjavafxapp/Recipe.fxml"));
      Parent recipesRoot = loader.load();

      Stage stage = (Stage) recipesButton.getScene().getWindow();
      Scene scene = new Scene(recipesRoot);
      stage.setScene(scene);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
