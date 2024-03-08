package userinterfacetools;

import static file_handling.FileHandler.writeFoodToFile;
import static userinterfacetools.TextBasedUI.*;

import entities.FoodItem;
import generaltools.InputHandler;
import generaltools.Selections;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import registers.FoodItemRegister;

/**
 * UserInterfaceMethods contains the methods for the UserInterface class. It provides
 * functionalities to:
 * <li>add a grocery item to the shopping list</li>
 * <li>remove a grocery item from the shopping list</li>
 * <li>print the shopping list</li>
 * <li>change the quantity of a grocery item in the shopping list</li>
 * <li>clear the shopping list</li>
 * <li>add a grocery item to the fridge</li>
 * <li>remove a grocery item from the fridge</li>
 * <li>print the fridge</li>
 * <li>change the quantity of a grocery item in the fridge</li>
 * <li>clear the fridge</li>
 * <li>add all grocery items from the shopping list to the fridge</li>
 * <li>add one grocery item from the shopping list to the fridge</li>
 *
 * @author Halvard Nordberg, Theodor Sjetnan Utvik
 * @version 0.0.2
 * @see FoodItemRegister
 * @see InputHandler
 * @see Selections
 * @see TextBasedUI
 * @see FoodItem
 * @see UserInterface
 * @since 27.02.2024
 */

public class UserInterfaceMethods {

  private FoodItemRegister groceryList;
  private FoodItemRegister fridge;
  private final InputHandler inputHandler;

  /**
   * Constructs a new UserInterfaceMethods object.
   */
  public UserInterfaceMethods() {
    //this.groceryList = new FoodItemRegister();
    //this.fridge = new FoodItemRegister();
    this.inputHandler = new InputHandler();
    setGroceryList();
    setFridge();
    //this.groceryList.initialize();
    //this.fridge.initialize();
  }

  /**
   * Sets the grocery list to a new FoodItemRegister object if it is null. Otherwise, it returns the
   * existing grocery list.
   *
   * @return FoodItemRegister The grocery list.
   */
  private FoodItemRegister setGroceryList() {
    if (groceryList == null) {
      groceryList = new FoodItemRegister();
      groceryList.initialize();
    }
    return groceryList;
  }

  /**
   * Sets the fridge to a new FoodItemRegister object if it is null. Otherwise, it returns the
   * existing fridge.
   *
   * @return FoodItemRegister The fridge.
   */
  private FoodItemRegister setFridge() {
    if (fridge == null) {
      fridge = new FoodItemRegister();
      fridge.initialize();
    }
    return fridge;
  }

  /**
   * Initializes the fridge by calling the initialize method in the FoodItemRegister class.
   */
  public void initializeFridge() {
    fridge.initialize();
  }

  /**
   * Initializes the shopping list by calling the initialize method in the FoodItemRegister class.
   */
  public void initializeShoppingList() {
    groceryList.initialize();
  }

  /**
   * Prompts the user to input details for a grocery item, including its name, food type, unit, and
   * quantity. These inputs are then used to create a new FoodItem object.
   *
   * @return FoodItem The newly created FoodItem object with user-provided details.
   */

  public FoodItem inputGroceryDetails() { // Name, foodtype, unit, quantity
    TextBasedUI.inputNameMessage();
    String groceryName = inputHandler.readString();
    // 1. Kjøtt, Meieri, Frukt, Grønnsaker, Brød, Korn, Krydder, Søtsaker, Drikke, Annet
    String groceryType = Selections.selectFoodType();
    //Gram, dl, stk
    String groceryUnit = Selections.selectUnit();
    TextBasedUI.inputQuantityMessage();
    int groceryQuantity = inputHandler.readInt();
    FoodItem foodItem = new FoodItem(groceryName, groceryType, groceryUnit, groceryQuantity);
    return foodItem;
  }


  /**
   * Adds a new grocery item to the shopping list. It prompts the user for item details and checks
   * if the item already exists in the list. If the item is new, it is added; otherwise, an existing
   * item message is displayed.
   */
  public void addGrocery(FoodItemRegister register) {
    FoodItem item = inputGroceryDetails();
    List<FoodItem> foodItemList = new ArrayList<>();
    foodItemList.add(item);

    if (!register.tryAddFoodItem(item)) {

      TextBasedUI.existsInList();
      return;
    }
    writeFoodToFile("src/main/resources/shoppingList.csv", foodItemList);
    groceryAdded();
  }

  /**
   * Removes a grocery item from the shopping list. It prompts the user for the name of the item to
   * remove and checks if the item exists in the list. If the item is found, it is removed;
   * otherwise, a "not in list" message is displayed.
   *
   * @param register The shopping list.
   */
  public void removeGrocery(FoodItemRegister register) {
    inputNameDelete();
    String name = inputHandler.readString();
    if (register.findFoodItem(name) == null) {
      notInList();
      return;
    }
    register.removeFoodItem(name);
    groceryRemoved();
  }

  /**
   * Prints the shopping list. It iterates through the list and prints each item in a formatted
   * manner.
   *
   * @param register The shopping list.
   */
  public void printList(FoodItemRegister register) {
    Iterator<FoodItem> iterator = register.getFoodItems();
    System.out.println(formatGroceryHeader());
    while (iterator.hasNext()) {
      FoodItem item = iterator.next();
      System.out.println(formatGrocery(item));
    }
  }

  /**
   * Changes the quantity of a grocery item in the shopping list. It prompts the user for the name
   * of the item and checks if it exists in the list. If the item is found, the user is prompted to
   * input a new quantity, which is then set for the item.
   *
   * @param register The shopping list.
   */
  public void changeQuantity(FoodItemRegister register) {
    inputNameChange();
    String name = inputHandler.readString();
    FoodItem item = register.findFoodItem(name);
    if (item != null) {
      inputQuantityChange();
      int quantity = inputHandler.readInt();
      item.setQuantity(quantity);
    }
  }

  /**
   * Clears the shopping list by removing all items from it.
   *
   * @param register The shopping list.
   * @param isFridge A boolean value indicating whether the list is the fridge or the shopping
   *                 list.
   */
  public void clearList(FoodItemRegister register, boolean isFridge) {
    register.removeAllItems();
    if (isFridge) {
      clearFridgeMessage();
    } else {
      clearShoppingListMessage();
    }
  }

  /**
   * The method print the fridge.
   */
  public void printFridge() {
    printList(fridge);
  }

  /**
   * The method prints the shopping list.
   */
  public void printShoppingList() {
    printList(groceryList);
  }

  /**
   * The methods will add a grocery item to the shopping list.
   */
  public void addGroceryShoppingList() {
    addGrocery(groceryList);
  }

  /**
   * The method will remove a grocery item from the shopping list.
   */
  public void removeGroceryShoppingList() {
    removeGrocery(groceryList);
  }

  /**
   * The method will change the quantity of a grocery item in the shopping list.
   */
  public void changeQuantityShoppingList() {
    changeQuantity(groceryList);
  }

  /**
   * The method will clear the shopping list.
   */
  public void clearShoppingList() {
    clearList(groceryList, false);
  }

  /**
   * The method will clear the fridge.
   */
  public void clearFridge() {
    clearList(fridge, true);
  }

  /**
   * The method will add a grocery to the fridge.
   */
  public void addGroceryFridge() {
    addGrocery(fridge);
  }

  /**
   * The method will remove a grocery from the fridge.
   */
  public void removeGroceryFridge() {
    removeGrocery(fridge);
  }

  /**
   * The method will change the quantity of a grocery item in the fridge.
   */
  public void changeQuantityFridge() {
    changeQuantity(fridge);
  }

  /**
   * The method will add one grocery item from the shopping list to the fridge.
   */
  public void addOneGroceryFridge() {
    TextBasedUI.inputNameToAdd();
    String name = inputHandler.readString();

    FoodItem item = groceryList.findFoodItem(name);
    if (item != null) {
      groceryList.removeFoodItem(name);

      fridge.tryAddFoodItem(item);

      itemMovedToFridge();
    } else {
      itemNotInList();
    }
  }

  /**
   * The method will add all grocery items from the shopping list to the fridge.
   */
  public void addAllGroceryFridge() {
    Iterator<FoodItem> iterator = groceryList.getFoodItems();
    while (iterator.hasNext()) {
      FoodItem item = iterator.next();
      fridge.tryAddFoodItem(item);
    }
    groceryList.removeAllItems();

    allItemsMovedToFridge();
  }


}
