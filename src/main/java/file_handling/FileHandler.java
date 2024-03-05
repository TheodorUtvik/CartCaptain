package file_handling;

import entities.FoodItem;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class FileHandler {

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

  public static void writeFoodToFile(String fileName, List<FoodItem> foodItems) {
    try {
      for (FoodItem foodItem : foodItems) {
        String line = String.format("%s,%s,%s,%.2f%n", foodItem.getName(), foodItem.getUnit(),
            foodItem.getFoodType(), foodItem.getQuantity());
        Files.write(Paths.get(fileName), line.getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
      }
    } catch (IOException e) {
      System.err.println("Failed to write to file: " + e.getMessage());
    }
  }

}
