package userinterfacetools;
import entities.FoodItem;
import generaltools.InputHandler;
import java.util.Iterator;
import registers.FoodItemRegister;

/**
 * Class for handling user interface methods. The class makes instances of the
 * FoodItemRegister class for the grocery list and the fridge.
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
   * Constructs a new UserInterfaceMethods.
   */
  public UserInterfaceMethods() {
    this.groceryList = new FoodItemRegister();
    this.fridge = new FoodItemRegister();
    this.inputHandler = new InputHandler();
  }

  public void initializeFridge() {
    fridge.initializeRegister();
  }

  public void initializeShoppingList() {
    groceryList.initializeRegister();
  }


  /*
      case 1:
        //addGrocery();
        break;
      case 2:
          //removeGrocery();
          break;
      case 3:
          //printGroceries();
          break;
      case 4:
          //clearList();
          break;
      case 5:
          //changeQuantity();
          break; */


  /**
   * Adds a grocery to the fridge. The method asks the user for: name, food type, unit and
   * quantity.
   *
   * @see InputHandler
   * @see textBasedUI
   * @see FoodItemRegister
   */
  public FoodItem inputGroceryDetails() { // Name, foodtype, unit, quantity
    textBasedUI.inputNameMessage();
    String groceryName = inputHandler.readString();
    textBasedUI.inputTypeMessage();
    String groceryType = inputHandler.readString();
    textBasedUI.inputUnitMessage();
    String groceryUnit = inputHandler.readString();
    textBasedUI.inputQuantityMessage();
    int groceryQuantity = inputHandler.readInt();
    FoodItem foodItem = new FoodItem(groceryName, groceryType, groceryUnit, groceryQuantity);
    return foodItem;
  }
  public void addGroceryToShoppingList(){

    if(!groceryList.tryAddFoodItem(inputGroceryDetails())){

      textBasedUI.existsInList();
      return;
    }
    textBasedUI.groceryAdded();
  }
  public void addGroceryToFridge(){

    if(!fridge.tryAddFoodItem(inputGroceryDetails())){

      textBasedUI.existsInList();
      return;
    }
    textBasedUI.groceryAdded();
  }
  /**
   * Deletes a grocery from the fridge. The method asks the user for: name
   *
   * @see InputHandler
   * @see textBasedUI
   * @see FoodItemRegister
   */
  public void removeGroceryFromFridge() {
    textBasedUI.inputNameDelete();
    String groceryName = inputHandler.readString();
    if(fridge.findFoodItem(groceryName) == null) {
      textBasedUI.notInList();
      return;
    }
    fridge.removeFoodItem(groceryName);
    textBasedUI.groceryRemoved();
  }
  public void removeGroceryFromShoppingList() {
    textBasedUI.inputNameDelete();
    String groceryName = inputHandler.readString();
    if(groceryList.findFoodItem(groceryName) == null) {
      textBasedUI.notInList();
      return;
    }
    groceryList.removeFoodItem(groceryName);
    textBasedUI.groceryRemoved();
  }

  public void printFoodInventory() {
    Iterator<FoodItem> iterator = fridge.getFoodItems();
    while (iterator.hasNext()) {
      FoodItem inventory = iterator.next();
      System.out.println(inventory.getDetails());
    }
  }
   public void printShoppingList() {
    Iterator<FoodItem> iterator = groceryList.getFoodItems();
    while (iterator.hasNext()) {
      FoodItem inventory = iterator.next();
      System.out.println(inventory.getDetails());
    }
  }

  public void changeQuantityFridge() {
    textBasedUI.inputNameChange();
    String grocery = inputHandler.readString();

    if (grocery.equals(fridge.findFoodItem(grocery).getName())) {
      textBasedUI.inputQuantityChange();
      int quantity = inputHandler.readInt();
      fridge.findFoodItem(grocery).setQuantity(quantity);
    }
  }

  public void changeQuantityShoppingList() {
    textBasedUI.inputNameChange();
    String grocery = inputHandler.readString();

    if (grocery.equals(groceryList.findFoodItem(grocery).getName())) {
      textBasedUI.inputQuantityChange();
      int quantity = inputHandler.readInt();
      groceryList.findFoodItem(grocery).setQuantity(quantity);
    }
  }

}
