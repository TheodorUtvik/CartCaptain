package controllers;

import entities.Recipe;
import file_handling.FileHandler;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class RecipeController {
  @FXML
  private VBox recipeBox;

  @FXML
  private void initialize() {
    // Create the initial HBox
    recipeBox.setSpacing(20);
    HBox hBox = new HBox();
    hBox.setSpacing(20);
    int recipeCount = 0; // Track the number of recipes added to the current HBox

    List<String> recipeNames = FileHandler.readLinesFromFile("src/main/resources/recipes.csv");
    for (String recipeName : recipeNames) {
      Recipe recipe = FileHandler.readRecipeFromFile("src/main/resources/recipes/" + recipeName);
      hBox.getChildren().add(recipeToVBox(recipe));
      recipeCount++;

      // If two recipes have been added to the current HBox, create a new one
      if (recipeCount % 2 == 0) {
        recipeBox.getChildren().add(hBox);
        hBox = new HBox();
        hBox.setSpacing(20);
      }
    }

    // If there's an odd number of recipes, add the last HBox
    if (recipeCount % 2 != 0) {
      recipeBox.getChildren().add(hBox);
    }
  }


  @FXML
  private VBox recipeToVBox(Recipe recipe) {
    VBox vBox = new VBox();
    ImageView imageView = new ImageView(new Image("file:" + recipe.getImage()));
    imageView.setFitHeight(200);
    imageView.setFitWidth(200);
    vBox.setSpacing(10);

    vBox.getChildren().add(imageView);
    vBox.getChildren().add(new Text(recipe.getRecipeName()));

    return vBox;
  }




}
