package controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MainController {

  @FXML
  private Button shoppingListButton;

  @FXML
  private Button fridgeButton;

  @FXML
  private Button recipesButton;

  @FXML
  private ImageView shoppingListImage;
  @FXML
  private ImageView fridgeImage;
  @FXML
  private ImageView recipesImage;

  @FXML
  private void onShoppingList() {
    try {
      FXMLLoader loader = new FXMLLoader(
          getClass().getResource("/scenebuilderjavafxapp/ShoppingListFrontPage.fxml"));
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
          getClass().getResource("/scenebuilderjavafxapp/FridgeFrontPage.fxml"));
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
          getClass().getResource("/scenebuilderjavafxapp/recipePage.fxml"));
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
