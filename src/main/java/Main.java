import static file_handling.FileHandler.readFoodFromFile;
import static file_handling.FileHandler.writeFoodToFile;

import entities.FoodItem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List; // Corrected import
import registers.FoodItemRegister;
import userinterfacetools.UserInterface;

public class Main {
  public static void main(String[] args) {
    //FoodItemRegister foodItemRegister = new FoodItemRegister();

    UserInterfaceMethods uiMethods = new UserInterfaceMethods();
    UserInterface ui = new UserInterface();
    ui.start();

    // Assuming getFoodItems() returns an Iterator<FoodItem>
    //Iterator<FoodItem> foodItemIterator = foodItemRegister.getFoodItems();

    // Convert the iterator to a list
    //List<FoodItem> foodItemList = new ArrayList<>(); // Corrected List declaration
    //while (foodItemIterator.hasNext()) {
      //foodItemList.add(foodItemIterator.next()); // Directly add FoodItem without converting to String
    //}

    UserInterface userInterface = new UserInterface();
    userInterface.start();

    // Now you can pass the list to the method
    //writeFoodToFile("src/main/resources/foodItems.csv", foodItemList);
    //readFoodFromFile("src/main/resources/foodItems.csv").forEachRemaining(System.out::println);
  }
}
