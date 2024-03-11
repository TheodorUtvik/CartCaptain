package registers;

import entities.FoodItem;
import file_handling.FileHandler;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import entities.Recipe;

/**
 * Class for storing Recipes.
 *
 * @author Sigurd Riseth
 * @version 0.0.2
 * @see Recipe
 * @since 26.02.2024
 */
public class RecipeRegister {
  /**
   * The register of recipes.
   */
  private final HashMap<String, Recipe> register;
  private int recipeCount;

  /**
   * Constructs a new empty RecipeRegister.
   */
  public RecipeRegister() {
    this.register = new HashMap<>();
    recipeCount = 0;
  }

  /**
   * Adds a recipe to the register.
   *
   * @param recipe the recipe to add
   */
  public boolean tryAddRecipe(Recipe recipe) {
    if(register.containsKey(recipe.getRecipeName())) {
      return false;
    }
    this.register.put(recipe.getRecipeName(), recipe);
    recipeCount++;
    return true;
  }
  public Iterator<Recipe> getRecipes() {
    return register.values().iterator();
  }
  /**
   * Removes a recipe from the register.
   *
   * @param name the name of the recipe to remove
   */
  public void removeRecipe(String name) {
    this.register.remove(name);
  }

  /**
   * Returns a recipe in the register by name.
   *
   * @param name the name of the recipe to find
   * @return the recipe with the specified name
  */

  public Recipe findRecipe(String name) {
     if (!register.containsKey(name)) {
       return null;
     }
    return this.register.get(name);
  }

  /**
   * Returns an iterator of recipes in the register by type.
   *
   * @param type the type of the recipes to find
   * @return an iterator of recipes with the specified type
   */
  public Iterator<Recipe> GetRecipesByType(String type) {
    return this.register.values().stream().filter(item -> item.getCuisineType().equals(type))
        .iterator();
  }

  public void intitializeRecipe(){
    //for (int i = 0; i < recipeCount; i++) {
    //  FileHandler.readFoodFromFile("src/main/resources/recipe" + i + ".csv").forEachRemaining(this::tryAddRecipe);
    //}
  }

}
