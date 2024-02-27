import java.util.Scanner;
import registers.Register;

public class UserInterface {

  Scanner menuChoice = new Scanner(System.in);

  public UserInterface() {
    Register register;
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
          goToFridge();
          break;
        case 3:
          goToRecipes();
          break;
        case 4:
          goToDropDownMenu();
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

  public void goToRecipes() {
    System.out.println("Recipes");
    textBasedUI.recipePrint();
    switch (menuChoice.nextInt()) {
      case 1:
        //addRecipe();
        break;
      case 2:
        //removeRecipe();
        break;
      case 3:
        //printRecipes();
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
        goToRecipes();
    }
  }

  public void goToFridge() {
    System.out.println("Fridge");
    textBasedUI.fridgePrint();
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
        goToFridge();
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

  public void goToDropDownMenu() {
    System.out.println("Drop down menu");
    textBasedUI.dropDownPrint();
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
        goToDropDownMenu();
    }
  }

}
