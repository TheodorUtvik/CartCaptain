package userinterfacetools;

import static userinterfacetools.TextBasedUI.formatGrocery;
import static userinterfacetools.TextBasedUI.formatGroceryHeader;
import static userinterfacetools.TextBasedUI.formatRecipe;

import entities.FoodItem;
import entities.Recipe;
import generaltools.InputHandler;
import registers.RecipeRegister;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * UserInterfaceRecipe facilitates interactions with recipes.
 * It provides functionalities to add, remove, and list recipes.
 *
 * @see RecipeRegister
 * @since 4.03.2024
 * @version 0.0.1
 * @author Simon Snildal
 */

public class UserInterfaceRecipe {

  private RecipeRegister recipeRegister;
  private InputHandler inputHandler;

  public UserInterfaceRecipe() {
    this.recipeRegister = new RecipeRegister();
    this.inputHandler = new InputHandler();
  }
  public void initializeRecipe() {
    recipeRegister.intitializeRecipe();
  }

  public void addRecipe() {
    TextBasedUI.recipeName();
    String name = inputHandler.readString();
    TextBasedUI.timeToCook();
    String timeToCook = inputHandler.readString();
    TextBasedUI.cuisineType();
    String cuisineType = inputHandler.readString();
    ArrayList<FoodItem> ingredients = new ArrayList<>();
    ArrayList<String> approach = new ArrayList<>();
    TextBasedUI.imgUrl();
    String image = inputHandler.readString();

    Recipe recipe = new Recipe(name, timeToCook, cuisineType, ingredients, approach, image);
    recipeRegister.tryAddRecipe(recipe);
    TextBasedUI.recipeAdded();
  }

  public void listRecipes() {
      Iterator<Recipe> iterator = recipeRegister.getRecipes();
      //System.out.println(formatRecipeHeader());
      while (iterator.hasNext()) {
        Recipe recipeList = iterator.next();
        System.out.println(formatRecipe(recipeList));
      }
    }

  public void removeRecipe() {
    TextBasedUI.recipeNameRemove();
    String name = inputHandler.readString();
    recipeRegister.removeRecipe(name);
    TextBasedUI.recipeRemoveSuccess();
  }

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
