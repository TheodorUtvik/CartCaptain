package userinterfacetools;
import static userinterfacetools.TextBasedUI.formatGrocery;
import static userinterfacetools.TextBasedUI.formatGroceryHeader;

import entities.FoodItem;
import generaltools.InputHandler;
import java.util.Iterator;
import registers.FoodItemRegister;

/**
 *
 * UserInterfaceMethods facilitates interactions with grocery lists and fridge inventories.
 * It provides functionalities to add, remove, and list groceries, as well as to modify quantities
 * and clear lists. It utilizes a TextBasedUI for user interaction and FoodItemRegister for managing
 * grocery items.
 *
 * @see FoodItemRegister
 * @since 27.02.2024
 * @version 0.0.1
 * @author Halvard Nordberg, Theodor Sjetnan Utvik
 */

public class UserInterfaceMethods {

  FoodItemRegister groceryList;
  FoodItemRegister fridge;
  InputHandler inputHandler;

  /**
  * Constructs a new instance of UserInterfaceMethods, initializing two FoodItemRegister instances
  * for managing groceries in a shopping list and a fridge, and an InputHandler for managing user input.
  */
  public UserInterfaceMethods() {
    this.groceryList = new FoodItemRegister();
    this.fridge = new FoodItemRegister();
    this.inputHandler = new InputHandler();
  }

  /**
   * Prompts the user to input details for a grocery item, including its name, food type, unit, and quantity.
   * These inputs are then used to create a new FoodItem object.
   *
   * @return FoodItem The newly created FoodItem object with user-provided details.
   */

  public FoodItem inputGroceryDetails() { // Name, foodtype, unit, quantity
    TextBasedUI.inputNameMessage();
    String groceryName = inputHandler.readString();
    // 1. Kjøtt, Meieri, Frukt, Grønnsaker, Brød, Korn, Krydder, Søtsaker, Drikke, Annet
    TextBasedUI.inputTypeMessage();
    String groceryType = inputHandler.readString();
    //Gram, dl, stk
    TextBasedUI.inputUnitMessage();
    String groceryUnit = inputHandler.readString();
    TextBasedUI.inputQuantityMessage();
    int groceryQuantity = inputHandler.readInt();
    FoodItem foodItem = new FoodItem(groceryName, groceryType, groceryUnit, groceryQuantity);
    return foodItem;
  }

  /**
   * Adds a new grocery item to the shopping list. It prompts the user for item details
   * and checks if the item already exists in the list. If the item is new, it is added;
   * otherwise, an existing item message is displayed.
   */
  public void addGroceryToShoppingList(){

    if(!groceryList.tryAddFoodItem(inputGroceryDetails())){

      TextBasedUI.existsInList();
      return;
    }
    TextBasedUI.groceryAdded();
  }

  /**
   * Adds a new grocery item to the fridge. It prompts the user for item details
   * and checks if the item already exists in the fridge. If the item is new, it is added;
   * otherwise, an existing item message is displayed.
   */
  public void addGroceryToFridge(){

    if(!fridge.tryAddFoodItem(inputGroceryDetails())){

      TextBasedUI.existsInList();
      return;
    }
    TextBasedUI.groceryAdded();
  }

  /**
   * Removes a specified grocery item from the fridge. The method prompts the user for the
   * name of the item. If the item exists, it is removed; if not, a not found message is displayed.
   */
  public void removeGroceryFromFridge() {
    TextBasedUI.inputNameDelete();
    String groceryName = inputHandler.readString();
    if(fridge.findFoodItem(groceryName) == null) {
      TextBasedUI.notInList();
      return;
    }
    fridge.removeFoodItem(groceryName);
    TextBasedUI.groceryRemoved();
  }

  /**
   * Removes a specified grocery item from the shopping list. The method prompts the user for the
   * name of the item. If the item exists, it is removed; if not, a not found message is displayed.
   */
  public void removeGroceryFromShoppingList() {
    TextBasedUI.inputNameDelete();
    String groceryName = inputHandler.readString();
    if(groceryList.findFoodItem(groceryName) == null) {
      TextBasedUI.notInList();
      return;
    }
    groceryList.removeFoodItem(groceryName);
    TextBasedUI.groceryRemoved();
  }


  /**
   * Prints the current inventory of groceries in the fridge, formatted with a header.
   * Each item is listed with its name, type, unit, and quantity.
   */
  public void printFoodInventory() {
    Iterator<FoodItem> iterator = fridge.getFoodItems();
    System.out.println(formatGroceryHeader());
    while (iterator.hasNext()) {
      FoodItem inventory = iterator.next();
      System.out.println(formatGrocery(inventory));
    }
  }

  /**
   * Prints the current list of groceries in the shopping list, formatted with a header.
   * Each item is listed with its name, type, unit, and quantity.
   */
  public void printShoppingList() {
    Iterator<FoodItem> iterator = groceryList.getFoodItems();
     System.out.println(formatGroceryHeader());
    while (iterator.hasNext()) {
      FoodItem inventory = iterator.next();
      System.out.println(formatGrocery(inventory));
    }
  }

  /**
   * Changes the quantity of an existing grocery item in the fridge. The method prompts the user for the
   * item's name and the new quantity. If the item exists, its quantity is updated.
   */
  public void changeQuantityFridge() {
    TextBasedUI.inputNameChange();
    String grocery = inputHandler.readString();

    if (grocery.equals(fridge.findFoodItem(grocery).getName())) {
      TextBasedUI.inputQuantityChange();
      int quantity = inputHandler.readInt();
      fridge.findFoodItem(grocery).setQuantity(quantity);
    }
  }

  /**
   * Changes the quantity of an existing grocery item in the shopping list. The method prompts the user for the
   * item's name and the new quantity. If the item exists, its quantity is updated.
   */
  public void changeQuantityShoppingList() {
    TextBasedUI.inputNameChange();
    String grocery = inputHandler.readString();

    if (grocery.equals(groceryList.findFoodItem(grocery).getName())) {
      TextBasedUI.inputQuantityChange();
      int quantity = inputHandler.readInt();
      groceryList.findFoodItem(grocery).setQuantity(quantity);
    }
  }

  /**
   * Clears all items from the fridge inventory, effectively emptying it.
   */
  public void clearListFridge() {
    fridge.removeAllItems();
  }

  /**
   * Clears all items from the shopping list, effectively emptying it.
   */
  public void clearListShoppingList() {
    groceryList.removeAllItems();
  }

}
