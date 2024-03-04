package userinterfacetools;

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

  private static RecipeRegister recipeRegister;
  private static InputHandler inputHandler;

  public UserInterfaceRecipe(RecipeRegister recipeRegister, InputHandler inputHandler) {
    this.recipeRegister = recipeRegister;
    this.inputHandler = inputHandler;
  }

  public static void addRecipe() {
    System.out.println("Enter recipe name:");
    String name = inputHandler.readString();
    System.out.println("Enter time to cook:");
    String timeToCook = inputHandler.readString();
    System.out.println("Enter cuisine type:");
    String cuisineType = inputHandler.readString();
    ArrayList<FoodItem> ingredients = new ArrayList<>();
    ArrayList<String> approach = new ArrayList<>();
    System.out.println("Enter image URL:");
    String image = inputHandler.readString();

    Recipe recipe = new Recipe(name, timeToCook, cuisineType, ingredients, approach, image);
    recipeRegister.addRecipe(recipe);
    System.out.println("Recipe added successfully!");
  }

  public static void listRecipes() {
    for (Recipe recipe : recipeRegister.getAllRecipes()) {
      System.out.println("Name: " + recipe.getRecipeName());
      System.out.println("Time to Cook: " + recipe.getTimeToCookRecipe());
      System.out.println("Cuisine Type: " + recipe.getCuisineType());
    }
  }

  public static void removeRecipe() {
    System.out.println("Enter the name of the recipe to remove:");
    String name = inputHandler.readString();
    recipeRegister.removeRecipe(name);
    System.out.println("Recipe removed successfully.");
  }

  public void findRecipe() {
    System.out.println("Enter the name of the recipe to find:");
    String name = inputHandler.readString();
    Recipe recipe = recipeRegister.findRecipe(name);
    if (recipe != null) {
      System.out.println("Name: " + recipe.getRecipeName());
    } else {
      System.out.println("Recipe not found.");
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
