package controllers;

import static controllers.SceneUtils.changeScene;

import entities.Recipe;
import file_handling.FileHandler;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RecipeController {
  @FXML
  private ScrollPane pane;
  @FXML
  private VBox recipeBox;
  @FXML
  private Button homeButton;
  @FXML
  private Button recipeButton;

  @FXML
  private void initialize() {
    pane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    pane.setFitToWidth(true);
    pane.setPadding(new javafx.geometry.Insets(10));

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

  /**
   * Toggles the visibility of the category buttons.
   * If the buttons are visible, they will be hidden and disabled.
   */
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
  private VBox recipeToVBox(Recipe recipe) {
    VBox vBox = new VBox();
    ImageView imageView = new ImageView(new Image("file:" + recipe.getImage()));
    imageView.setFitHeight(200);
    imageView.setFitWidth(200);
    vBox.setSpacing(10);

    vBox.getChildren().add(imageView);
    vBox.getChildren().add(new Text(recipe.getRecipeName()));

    vBox.setOnMouseClicked(
        event -> openRecipePage(recipe));

    return vBox;
  }

  private void openRecipePage(Recipe recipe) {
    Stage recipeStage = new Stage();
    BorderPane recipePane = new BorderPane();

    VBox mainVBox = new VBox();
    mainVBox.setSpacing(20);
    mainVBox.setAlignment(Pos.TOP_CENTER);

    VBox headerVBox = new VBox();
    headerVBox.setAlignment(Pos.TOP_CENTER);

    ImageView recipeImage = new ImageView(new Image("file:" + recipe.getImage()));
    recipeImage.setFitHeight(200);
    recipeImage.setFitWidth(200);

    Label recipeNameLabel = new Label(recipe.getRecipeName());
    recipeNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));

    headerVBox.getChildren().addAll(recipeImage, recipeNameLabel);

    HBox contents = new HBox();
    contents.setSpacing(30);
    VBox ingredientsVBox = createVerticalBoxWithLabels("Ingredienser", recipe.getIngredients(), false);
    VBox approachVBox = createVerticalBoxWithLabels("Fremgangsmåte", recipe.getApproach(), true);
    Button purchaseButton = new Button("Legg til i handleliste");
    purchaseButton.setOnAction(event -> {
      FileHandler.writeShoppingListToFile("src/main/resources/shoppingList.csv", recipe.getIngredients());
    });
    ingredientsVBox.getChildren().add(purchaseButton);
    contents.getChildren().addAll(ingredientsVBox, approachVBox);
    contents.setAlignment(Pos.TOP_CENTER);

    mainVBox.getChildren().addAll(headerVBox, contents);
    recipePane.setCenter(mainVBox);

    Scene recipeScene = new Scene(recipePane, 1000, 800);
    recipeStage.setScene(recipeScene);

    recipeStage.setTitle(recipe.getRecipeName());
    recipeStage.show();
  }

  private VBox createVerticalBoxWithLabels(String title, List<String> items, boolean wrapText) {
    VBox vBox = new VBox();
    vBox.setSpacing(10);

    Label titleLabel = new Label(title);
    titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

    vBox.getChildren().add(titleLabel);

    for (int i = 0; i < items.size(); i++) {
      String item = items.get(i);
      Label label = new Label((i + 1) + ": " + item);
      if (wrapText) {
        label.setWrapText(true);
        label.setMaxWidth(250);
      }
      vBox.getChildren().add(label);
    }

    return vBox;
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




}
