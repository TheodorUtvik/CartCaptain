import entities.FoodItem;
import registers.FoodItemRegister;
import userinterfacetools.UserInterface;
import userinterfacetools.UserInterfaceMethods;

public class Main {
  public static void main(String[] args) {

    UserInterfaceMethods uiMethods = new UserInterfaceMethods();
    UserInterface ui = new UserInterface();
    ui.initializeFridge();
    ui.initializeShoppingList();
    uiMethods.printFoodInventory();
    uiMethods.printShoppingList();
    ui.start();


  }


}
