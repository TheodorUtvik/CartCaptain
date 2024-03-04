package userinterfacetools;

import static userinterfacetools.TextBasedUI.*;
import entities.FoodItem;
import generaltools.InputHandler;
import generaltools.Selections;
import java.util.Iterator;
import registers.FoodItemRegister;

public class UserInterfaceMethods {

  FoodItemRegister groceryList;
  FoodItemRegister fridge;
  InputHandler inputHandler;

  public UserInterfaceMethods() {
    //this.groceryList = new FoodItemRegister();
    //this.fridge = new FoodItemRegister();
    this.inputHandler = new InputHandler();
    setGroceryList();
    setFridge();
    //this.groceryList.initialize();
    //this.fridge.initialize();
  }
  private FoodItemRegister setGroceryList() {
    if (groceryList == null) {
      groceryList = new FoodItemRegister();
      groceryList.initialize();
    }
    return groceryList;
  }
  private FoodItemRegister setFridge() {
    if (fridge == null) {
      fridge = new FoodItemRegister();
      fridge.initialize();
    }
    return fridge;
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
    String groceryType = Selections.selectFoodType();
    //Gram, dl, stk
    String groceryUnit = Selections.selectUnit();
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
  public void addGrocery(FoodItemRegister register) {
    FoodItem item = inputGroceryDetails();

    if(!register.tryAddFoodItem(item)){

      TextBasedUI.existsInList();
      return;
    }
    groceryAdded();
  }

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

  public void printList(FoodItemRegister register) {
    Iterator<FoodItem> iterator = register.getFoodItems();
    System.out.println(formatGroceryHeader());
    while (iterator.hasNext()) {
      FoodItem item = iterator.next();
      System.out.println(formatGrocery(item));
    }
  }

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

  public void clearList(FoodItemRegister register, boolean isFridge) {
    register.removeAllItems();
    if (isFridge) {
      clearFridgeMessage();
    } else {
      clearShoppingListMessage();
    }
  }
  public void printFridge() {
    printList(fridge);
  }
  public void printShoppingList() {
    printList(groceryList);
  }
  public void addGroceryShoppingList() {
    addGrocery(groceryList);
  }
  public void removeGroceryShoppingList() {
    removeGrocery(groceryList);
  }
  public void changeQuantityShoppingList() {
    changeQuantity(groceryList);
  }
  public void clearShoppingList() {
    clearList(groceryList, false);
  }
  public void clearFridge() {
    clearList(fridge, true);
  }
  public void addGroceryFridge() {
    addGrocery(fridge);
  }
  public void removeGroceryFridge() {
    removeGrocery(fridge);
  }
  public void changeQuantityFridge() {
    changeQuantity(fridge);
  }



}
