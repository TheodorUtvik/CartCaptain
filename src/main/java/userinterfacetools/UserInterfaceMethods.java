package userinterfacetools;
import generaltools.InputHandler;
import registers.FoodItemRegister;
import userinterfacetools.textBasedUI;

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
   * Adds a grocery to the grocery list. The method asks the user for: name, food type, unit and
   * quantity.
   *
   * @see InputHandler
   * @see textBasedUI
   * @see FoodItemRegister
   */
  public void addGrocery() { // Name, foodtype, unit, quantity
    textBasedUI.inputNameMessage();
    String groceryName = inputHandler.readString();
    textBasedUI.inputNameMessage();
    String groceryType = inputHandler.readString();
    textBasedUI.inputTypeMessage();
    String groceryUnit = inputHandler.readString();
    textBasedUI.inputUnitMessage();
    int groceryQuantity = inputHandler.readInt();
    textBasedUI.inputQuantityMessage();
    groceryList.addFoodItem(groceryName, groceryType, groceryUnit, groceryQuantity);
  }

}
