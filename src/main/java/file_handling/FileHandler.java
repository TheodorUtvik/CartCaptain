package file_handling;

import entities.FoodItem;
import entities.Recipe;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * FileHandler provides methods for reading and writing <code>FoodItem</code> objects to and from a
 * file.
 *
 * @author Sigurd Riseth, Theodor Sjetnan Utvik
 * @version 0.0.2
 * @see FoodItem
 * @see Recipe
 * @since 05.03.2024
 */
public class FileHandler {

  /**
   * Reads a file and returns an iterator of <code>FoodItem</code> objects.
   *
   * @param fileName the name of the file to read
   * @return an iterator of <code>FoodItem</code> objects
   */
  public static Iterator<FoodItem> readFoodFromFile(String fileName) {
    List<FoodItem> foodItems = new ArrayList<>();

    // Use try-with-resources to ensure the scanner is closed properly
    try (Scanner scanner = new Scanner(Files.newBufferedReader(Paths.get(fileName)))) {
      scanner.useDelimiter(",|\\R"); // Regex for comma or any line terminator

      while (scanner.hasNext()) {
        try {
          String name = scanner.next().trim(); // Trim to remove leading and trailing spaces
          String unit = scanner.next().trim();
          String foodType = scanner.next().trim();
          double quantity = Double.parseDouble(scanner.next().trim());

          foodItems.add(new FoodItem(name, foodType, unit, quantity));
        } catch (NumberFormatException e) {
          System.err.println("Error parsing quantity to integer: " + e.getMessage());
        }
      }
    } catch (IOException e) {
      System.err.println("Failed to read the file: " + e.getMessage());
    }

    return foodItems.iterator();
  }

  /**
   * Writes a list of <code>FoodItem</code> objects to a file.
   *
   * @param fileName  the name of the file to write to
   * @param foodItems the list of <code>FoodItem</code> objects to write
   */
  public static void writeFoodToFile(String fileName, List<FoodItem> foodItems) {
    try {
      for (FoodItem foodItem : foodItems) {
        String line = String.format("%s,%s,%s,%.2f%n", foodItem.getName(), foodItem.getUnit(),
            foodItem.getFoodType(), foodItem.getQuantity());
        Files.write(Paths.get(fileName), line.getBytes(), StandardOpenOption.APPEND,
            StandardOpenOption.CREATE);
      }
    } catch (IOException e) {
      System.err.println("Failed to write to file: " + e.getMessage());
    }
  }

  /**
   * Clears the content of a file.
   *
   * @param fileName the name of the file to clear
   */
  public static void clearFile(String fileName) {
    try {
      Files.write(Paths.get(fileName), "".getBytes());
    } catch (IOException e) {
      System.err.println("Failed to clear file: " + e.getMessage());
    }
  }

  /**
   * Writes a <code>Recipe</code> object to a file.
   *
   * @param fileName the name of the file to write to
   * @param recipe the <code>Recipe</code> object to write
   */
  public static void writeRecipeToFile(String fileName, Recipe recipe) {
    try {
      String recipeString = String.format("%s%n%s%n%s%n%s%n", recipe.getRecipeName(), recipe.getTimeToCookRecipe(),
          recipe.getCuisineType(), recipe.getImage());
      List<String> ingredients = recipe.getIngredients();
      for (String ingredient : ingredients) {
        recipeString += ingredient + "\n";
      }
      recipeString += "\n"; // Add a blank line to separate ingredients and approach
      List<String> approach = recipe.getApproach();
      for (String step : approach) {
        recipeString += step + "\n";
      }
      Files.write(Paths.get(fileName), recipeString.getBytes(), StandardOpenOption.APPEND,
          StandardOpenOption.CREATE);
    } catch (IOException e) {
      System.err.println("Failed to write to file: " + e.getMessage());
    }
  }

  /**
   * Reads a file and returns a <code>Recipe</code> object.
   *
   * @param fileName the name of the file to read
   * @return a <code>Recipe</code> object
   */
  public static Recipe readRecipeFromFile(String fileName) {
    Recipe recipe = null;
    try (Scanner scanner = new Scanner(Files.newBufferedReader(Paths.get(fileName)))) {
      String recipeName = scanner.nextLine();
      String timeToCook = scanner.nextLine();
      String cuisineType = scanner.nextLine();
      String imageUrl = scanner.nextLine();

      ArrayList<String> ingredients = new ArrayList<>();
      ArrayList<String> approach = new ArrayList<>();

      // Les innholdet i filen og del det inn i ingredienser og fremgangsmåter
      boolean isApproach = false;
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        if (line.isEmpty()) {
          isApproach = true; // Når det er en tom linje, markerer det slutten på ingrediensene
          continue;
        }
        if (!isApproach) {
          ingredients.add(line);
        } else {
          approach.add(line);
        }
      }

      recipe = new Recipe(recipeName, timeToCook, cuisineType, ingredients, approach, imageUrl);
    } catch (IOException e) {
      System.err.println("Failed to read the file: " + e.getMessage());
    }
    return recipe;
  }


  /**
   * The method deletes a file from the system.
   *
   * @param fileName the name of the file to delete
   * @return true if the file was deleted, false otherwise.
   */
  public static void deleteFile(String fileName) {
    try {
      Files.delete(Paths.get(fileName));
    } catch (IOException e) {
      System.err.println("Failed to delete file: " + e.getMessage());
    }
  }

}
