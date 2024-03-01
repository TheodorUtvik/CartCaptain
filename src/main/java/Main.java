import entities.FoodItem;
import registers.FoodItemRegister;
import userinterfacetools.UserInterface;
import userinterfacetools.UserInterfaceMethods;

public class Main {
  public static void main(String[] args) {
    //tissetass

    FoodItemRegister initialize = new FoodItemRegister();
    UserInterfaceMethods uiMethods = new UserInterfaceMethods();

    uiMethods.initializeFridge();
    uiMethods.initializeShoppingList();

    UserInterface ui = new UserInterface();
    ui.start();

  }


}
