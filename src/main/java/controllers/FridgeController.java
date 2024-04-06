package controllers;

import static controllers.SceneUtils.changeScene;

import entities.FoodItem;
import file_handling.FileHandler;
import java.util.Iterator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FridgeController {

  @FXML
  private TextField searchField;

  @FXML
  private ChoiceBox<Button> choiceBox;

  @FXML
  private Button changeAmountButton;

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
  private Button shoppingListButton;

  public ImageView homeButtonImage;

  @FXML
  public ImageView recipeButtonImage;

  @FXML
  public ImageView shoppingListButtonImage;

  private String foodItemDisplay(FoodItem foodItem) {
    return foodItem.getName() + " - " + foodItem.getQuantity() + " " + foodItem.getUnit();
  }

  @FXML
  public void changeAmount(ActionEvent event) {
    String selectedItem = fridgeListView.getSelectionModel().getSelectedItem();
    String[] itemDetails = selectedItem.split(" - ");
    String itemName = itemDetails[0];
    String[] itemQuantity = itemDetails[1].split(" ");
    showAmountChangeDialog(itemName, itemQuantity);
  }

  private void showAmountChangeDialog(String itemName, String[] itemQuantity) {
    Stage amountStage = new Stage();
    BorderPane pane = new BorderPane();

    VBox box = new VBox();
    box.setAlignment(Pos.CENTER);
    box.setSpacing(10);

    TextField amountField = new TextField();
    amountField.promptTextProperty().setValue("Ny mengde...");
    amountField.setMaxWidth(200);

    Text amountText = new Text("Her kan du endre mengden " + itemName + " fra " + itemQuantity[0] + " " + itemQuantity[1]);
    Text errorText = new Text();
    errorText.setVisible(false);
    errorText.setStyle("-fx-fill: red");

    HBox saveButtons = new HBox();
    saveButtons.setAlignment(Pos.CENTER);
    saveButtons.setSpacing(10);
    Button saveButton = new Button("Lagre");
    Button cancelButton = new Button("Avbryt");
    Button removeButton = new Button("Slett");
    saveButtons.getChildren().addAll(cancelButton, saveButton, removeButton);

    cancelButton.setOnAction(event -> amountStage.close());
    saveButton.setOnAction(event -> {
      try {
        String newAmount = amountField.getText();
        FileHandler.changeFoodItemQuantity("src/main/resources/foodItems.csv", itemName,
            newAmount);
      updateFridgeListView();
      } catch (Exception e) {
        errorText.setText("En feil oppsto. Vennligs skriv inn et gyldig tall.");
        errorText.setVisible(true);
      }
    });
    removeButton.setOnAction(event -> {
      try {
        FileHandler.removeFoodItem("src/main/resources/foodItems.csv", itemName);
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

  private void updateFridgeListView() {
    fridgeListView.getItems().clear();
    Iterator<FoodItem> allFoodItems = FileHandler.readFoodFromFile(
        "src/main/resources/foodItems.csv");
    while (allFoodItems.hasNext()) {
      FoodItem foodItem = allFoodItems.next();
      fridgeListView.getItems().add(foodItemDisplay(foodItem));
    }
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
