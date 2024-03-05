package file_handling;

import entities.FoodItem;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class FileHandler {

  public static Iterator<FoodItem> readFoodFromFile(String fileName) {
    ArrayList<FoodItem> foodItems = new ArrayList<>();
    try (Scanner scanner = new Scanner(Path.of(fileName))) {
      scanner.useDelimiter(", |[\n]");
      while (scanner.hasNextLine()) {
        String name = scanner.next();
        String foodType = scanner.next();
        String unit = scanner.next();
        int quantity = scanner.nextInt();
        foodItems.add(new FoodItem(name, foodType, unit, quantity));
      }
    } catch (Exception e) {
      System.out.println("Error reading file: " + e.getMessage());
    }
      return foodItems.iterator();
  }

}
