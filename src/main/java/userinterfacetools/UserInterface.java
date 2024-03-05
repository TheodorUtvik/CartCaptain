package userinterfacetools;

import java.util.Scanner;
import registers.FoodItemRegister;

public class UserInterface {

  Scanner menuChoice = new Scanner(System.in);
  private UserInterfaceMethods uiMethod;
  private UserInterfaceRecipe uiRecipe;

  public UserInterface() {
    this.uiMethod = new UserInterfaceMethods();
    this.uiRecipe = new UserInterfaceRecipe();
  }



  public void start() {
    uiMethod.initializeFridge();
    uiMethod.initializeShoppingList();
    uiRecipe.initializeRecipe();
    TextBasedUI.welcomeMessage();
    boolean running = true;
    while (running) {
      TextBasedUI.menuPrint();
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
    TextBasedUI.recipePrint();
    switch (menuChoice.nextInt()) {
      case 1:
        uiRecipe.addRecipe();
        break;
      case 2:
        uiRecipe.removeRecipe();
        break;
      case 3:
        uiRecipe.listRecipes();
        break;
      case 4:
        uiRecipe.searchRecipe();
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
    TextBasedUI.fridgePrint();
    switch (menuChoice.nextInt()) {
      case 1:
        uiMethod.addGroceryFridge();
        break;
      case 2:
        uiMethod.removeGroceryFridge();
        break;
      case 3:
        //uiMethod.printList(foodItemRegister);
        uiMethod.printFridge();
        break;
      case 4:
        uiMethod.clearFridge();
        break;
      case 5:
        uiMethod.changeQuantityFridge();
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
    TextBasedUI.shoppingListPrint();
    switch (menuChoice.nextInt()) {
      case 1:
        uiMethod.addGroceryShoppingList();
        break;
      case 2:
        uiMethod.removeGroceryShoppingList();
        break;
      case 3:
        uiMethod.printShoppingList();
        break;
      case 4:
        uiMethod.clearShoppingList();
        break;
      case 5:
        uiMethod.changeQuantityShoppingList();
        break;
      case 6:
        addGroceryFridge();
        break;
      case 7:
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

  private void addGroceryFridge() {
    TextBasedUI.addGroceryToFridgeMenu();
    switch (menuChoice.nextInt()){
      case 1:
        uiMethod.addOneGroceryFridge();
        break;
      case 2:
        uiMethod.addAllGroceryFridge();
        break;
      case 3:
        start();
        break;
      default:
        System.out.println("Invalid choice. Try again.");
        addGroceryFridge();
    }
  }

  public void goToDropDownMenu() {
    System.out.println("Drop down menu");
    TextBasedUI.dropDownPrint();
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
