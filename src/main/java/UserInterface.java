import java.util.Scanner;

public class UserInterface {

  Scanner menuChoice = new Scanner(System.in);

  public UserInterface() {
    private FoodItemRegister register;
    //private final InputHandler inputHandler;
  }

  public void start() {
    boolean running = true;
    while (running) {
      textBasedUI.menuPrint();
      switch (menuChoice.nextInt()) {
        case 1:
          goToShoppingList();
          break;
        case 2:
          //goToFridge();
          break;
        case 3:
          //goToRecipes();
          break;
        case 4:
          //goToDropDown();
          break;
        case 0:
          System.out.println("Goodbye!");
          running = false;
          break;
        default:
          System.out.println("Invalid choice. Try again.");
          start();
      }
    }
  }

  public void goToShoppingList() {
    System.out.println("Shopping list");
    textBasedUI.shoppingListPrint();
    switch (menuChoice.nextInt()) {
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
        break;
      case 6:
        start();
        break;
      case 0:
        System.out.println("Goodbye!");
        break;
      default:
        System.out.println("Invalid choice. Try again.");
        goToShoppingList();
    }
  }

}
