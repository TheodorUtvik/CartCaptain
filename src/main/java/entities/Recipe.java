package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.List;

/**
 * Entity class for a recipe which is to be included in the program.  The class includes fields:
 * <li>Recipe Name</li>
 * <li>Time to cook recipe</li>
 * <li>Cuisine Type</li>
 * <li>Ingredients</li>
 * <li>Approach</li>
 * <li>Image</li>
 *
 * @author Theodor Sjetnan Utvik
 * @version 0.0.2
 * @since 26.02.2024
 */

public class Recipe {

  private String recipeName;
  private String timeToCookRecipe;
  private String cuisineType;
  private ArrayList<String> ingredients;
  private ArrayList<String> approach;
  private String image;


  /**
   * Constructor for the Recipe class. The constructor takes in the following parameters:
   *
   * @param recipeName       is the name of the recipe.
   * @param timeToCookRecipe is the time it takes to cook the recipe.
   * @param cuisineType      is the type of cuisine the recipe is.
   * @param ingredients      is a list of ingredients for the recipe.
   * @param approach         is a list of steps to cook the recipe.
   * @param image            is the image for the recipe.
   */
  public Recipe(String recipeName, String timeToCookRecipe, String cuisineType,
      ArrayList<String> ingredients, ArrayList<String> approach, String image) {
    setRecipeName(recipeName);
    setTimeToCookRecipe(timeToCookRecipe);
    setCuisineType(cuisineType);
    setIngredients(ingredients);
    setApproach(approach);
    setImage(image);
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
   *
   * @param recipeName is validated
   */
  public void setRecipeName(String recipeName) {
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
  public String getTimeToCookRecipe() {
    return timeToCookRecipe;
  }

  /**
   * The method validates if the value for the time to cook a recipe, is a valid value.
   *
   * @param timeToCookRecipe is validated.
   */
  public void setTimeToCookRecipe(String timeToCookRecipe) {
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
  public String getCuisineType() {
    return cuisineType;
  }

  /**
   * The method validates if the cuisine type value is valid.
   *
   * @param cuisineType is validated.
   */
  public void setCuisineType(String cuisineType) {
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
  public ArrayList<String> getIngredients() {
    return ingredients;
  }

  /**
   * Empty method to set the ingredients.
   *
   * @param ingredients
   */
  public void setIngredients(ArrayList<String> ingredients) {
    this.ingredients = ingredients;
  }

  /**
   * Sets the approach for the recipe. The approach is a list of strings.
   *
   * @param approach is the approach for the recipe.
   */
  public void setApproach(ArrayList<String> approach) {
    this.approach = approach;
  }

  /**
   * Gets the approach for the recipe. The approach is a list of strings.
   *
   * @return the approach for the recipe.
   */
  public ArrayList<String> getApproach() {
    return approach;
  }

  /**
   * Sets the image for the recipe.
   *
   * @param image is the image for the recipe.
   */
  public void setImage(String image) {
    this.image = image;
  }

  /**
   * Gets the image for the recipe.
   *
   * @return the image for the recipe.
   */
  public String getImage() {
    return image;
  }
}
