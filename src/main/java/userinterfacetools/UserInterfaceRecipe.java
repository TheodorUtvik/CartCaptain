package userinterfacetools;

import static userinterfacetools.TextBasedUI.formatGrocery;
import static userinterfacetools.TextBasedUI.formatGroceryForRecipe;
import static userinterfacetools.TextBasedUI.formatGroceryHeader;
import static userinterfacetools.TextBasedUI.formatRecipe;

import entities.FoodItem;
import entities.Recipe;
import generaltools.InputHandler;
import java.util.List;
import registers.RecipeRegister;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * UserInterfaceRecipe facilitates interactions with recipes. It provides functionalities to add,
 * remove, and list recipes.
 *
 * @author Simon Snildal, Halvard Nordberg
 * @version 0.0.1
 * @see RecipeRegister
 * @since 4.03.2024
 */

public class UserInterfaceRecipe {

  private RecipeRegister recipeRegister;
  private InputHandler inputHandler;
  private UserInterfaceMethods uim;

  /**
   * Constructs a new UserInterfaceRecipe object.
   */
  public UserInterfaceRecipe() {
    this.recipeRegister = new RecipeRegister();
    this.inputHandler = new InputHandler();
    this.uim = new UserInterfaceMethods();
  }

  /**
   * Initializes the recipe register.
   */
  public void initializeRecipe() {
    recipeRegister.intitializeRecipe();
  }

  /**
   * Adds a recipe to the recipe register.  It does so with the following attributes:
   * <li>name</li>
   * <li>time to cook</li>
   * <li>cuisine type</li>
   * <li>ingredients</li>
   * <li>approach</li>
   * <li>image</li>
   */
  public void addRecipe() {
    TextBasedUI.recipeName();
    String name = inputHandler.readString();
    TextBasedUI.timeToCook();
    String timeToCook = inputHandler.readString();
    TextBasedUI.cuisineType();
    String cuisineType = inputHandler.readString();
    ArrayList<String> ingredients;
    ingredients = (ArrayList<String>) addIngredients();
    ArrayList<String> approach;
    approach = (ArrayList<String>) addApproach();
    TextBasedUI.imgUrl();
    String image = inputHandler.readString();

    Recipe recipe = new Recipe(name, timeToCook, cuisineType,
        ingredients, approach, image);
    recipeRegister.tryAddRecipe(recipe);
    TextBasedUI.recipeAdded();
  }
  public List<String> addIngredients() {
    List<String> ingredients = new ArrayList<>();
    boolean adding = true;
    while (adding) {
      FoodItem item = uim.inputGroceryDetails();
      ingredients.add(formatGroceryForRecipe(item));
      TextBasedUI.addAnotherIngredient();
      int inputFromUser = inputHandler.readInt();
      if (inputFromUser == 2) {
        adding = false;
      }
    }
    return ingredients;
  }
  public List<String> addApproach() {
    List<String> approach = new ArrayList<>();
    boolean adding = true;
    while (adding) {
      TextBasedUI.addApproach();
      String step = inputHandler.readString();
      approach.add(step);
      TextBasedUI.addAnotherStep();
      int inputFromUser = inputHandler.readInt();
      if (inputFromUser == 2) {
        adding = false;
      }
    }
    return approach;
  }

  /**
   * Lists all recipes in the recipe register. It is done with an iterator over the recipe
   * register.
   */
  public void listRecipes() {
    Iterator<Recipe> iterator = recipeRegister.getRecipes();
    while (iterator.hasNext()) {
      Recipe recipeList = iterator.next();
      System.out.println(formatRecipe(recipeList));
    }
  }

  /**
   * Removes a recipe from the recipe register. It prompts the user for the name of the recipe to
   * remove. When the recipe is removed, a success message is displayed.
   */
  public void removeRecipe() {
    TextBasedUI.recipeNameRemove();
    String name = inputHandler.readString();
    if(recipeRegister.findRecipe(name) == null) {
      TextBasedUI.recipeNotFound();
      return;
    }
    recipeRegister.removeRecipe(name);
    TextBasedUI.recipeRemoveSuccess();
  }

  /**
   * Searches for a recipe in the recipe register. It prompts the user for the name of the recipe to
   * search for. If the recipe is found, it is displayed; otherwise, a "not found" message is
   * displayed.
   */
  public void searchRecipe() {
    TextBasedUI.recipeName();
    String name = inputHandler.readString();
    Recipe recipe = recipeRegister.findRecipe(name);
    if (recipe != null) {

      System.out.println(formatRecipe(recipe));
    } else {
      TextBasedUI.recipeNotFound();
    }
  }

  /**
   * Lists recipes by type. It prompts the user for the cuisine type to list recipes for. If no
   * recipes are found, a "not found" message is displayed. Otherwise, the recipes are displayed.
   *
   * @see RecipeRegister
   */
  public void listRecipesByType() {
    System.out.println("Enter the cuisine type to list recipes:");
    String type = inputHandler.readString();
    Iterator<Recipe> iterator = recipeRegister.GetRecipesByType(type);
    if (!iterator.hasNext()) {
      System.out.println("No recipes found for this cuisine type.");
      return;
    }
    while (iterator.hasNext()) {
      Recipe recipe = iterator.next();
      System.out.println("Name: " + recipe.getRecipeName());
    }
  }
}
