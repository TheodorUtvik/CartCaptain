import java.util.ArrayList;

/**
 * Entity class for a recipe which is to be included in the program. The class includes fields:
 * <li>Recipe Name</li>
 * <li>Time to cook recipe</li>
 * <li>Cuisine Type</li>
 *
 * @version 0.0.1
 * @author Theodor Sjetnan Utvik
 * @since 26.02.2024
 */

public class Recipe {

  private String recipeName;
  private String timeToCookRecipe;
  private String cuisineType;
  private ArrayList<FoodItem> ingredients;

  public Recipe(String recipeName, String timeToCookRecipe, String cuisineType, ArrayList<FoodItem> ingredients) {
    setRecipeName(recipeName);
    setTimeToCookRecipe(timeToCookRecipe);
    setCuisineType(cuisineType);
    setIngredients(ingredients);
  }


  /**
   * Gets the name of the recipe.
   *
   * @return recipe name
   */
  public String getRecipeName() {
    return recipeName;
  }

  /**
   * Validates that the recipe is not a null value. If it is invalid, a correction String is set.
   * @param recipeName is validated
   */
  private void setRecipeName(String recipeName) {
    if (recipeName != null) {
      this.recipeName = recipeName;
    } else {
      this.recipeName = "INVALID RECIPE NAME";
    }
  }

  /**
   * Gets the time which is needed to cook the recipe.
   *
   * @return time to cook recipe
   */
  private String getTimeToCookRecipe() {
    return timeToCookRecipe;
  }

  /**
   * The method validates if the value for the time to cook a recipe, is a valid value.
   *
   * @param timeToCookRecipe is validated.
   */
  private void setTimeToCookRecipe(String timeToCookRecipe) {
    if (timeToCookRecipe != null) {
      this.timeToCookRecipe = timeToCookRecipe;
    } else {
      this.timeToCookRecipe = "INVALID TIME TO COOK RECIPE";
    }
  }

  /**
   * Gets the cuisine type for the recipe.
   *
   * @return cuisine type
   */
  private String getCuisineType() {
    return cuisineType;
  }

  /**
   * The method validates if the cuisine type value is valid.
   *
   * @param cuisineType is validated.
   */
  private void setCuisineType(String cuisineType) {
    if (cuisineType != null) {
      this.cuisineType = cuisineType;
    } else {
      this.cuisineType = "INVALID CUISINE TYPE";
    }
  }


  /**
   * Gets the ingredients in a list.
   *
   * @return ingredients listed.
   */
  public ArrayList<FoodItem> getIngredients() {
    return ingredients;
  }

  /**
   * Empty method to set the ingredients.
   *
   * @param ingredients
   */
  public void setIngredients(ArrayList<FoodItem> ingredients) {
    this.ingredients = ingredients;
  }
}
