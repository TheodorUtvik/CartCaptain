package userinterfacetools;

import static userinterfacetools.TextBasedUI.*;
import entities.FoodItem;
import generaltools.InputHandler;
import java.util.Iterator;
import registers.FoodItemRegister;

public class UserInterfaceMethods {

  FoodItemRegister groceryList;
  FoodItemRegister fridge;
  InputHandler inputHandler;

  public UserInterfaceMethods() {
    this.groceryList = new FoodItemRegister();
    this.fridge = new FoodItemRegister();
    this.inputHandler = new InputHandler();

    this.groceryList.initialize();
    this.fridge.initialize();
  }

  public FoodItem inputGroceryDetails() {
    inputNameMessage();
    String name = inputHandler.readString();
    inputTypeMessage();
    String type = inputHandler.readString();
    inputUnitMessage();
    String unit = inputHandler.readString();
    inputQuantityMessage();
    int quantity = inputHandler.readInt();
    return new FoodItem(name, type, unit, quantity);
  }

  public void addGrocery(FoodItemRegister register) {
    FoodItem item = inputGroceryDetails();
    if (!register.tryAddFoodItem(item)) {
      existsInList();
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

}
