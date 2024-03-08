package userinterfacetools;

import java.util.Scanner;

/**
 * The {@code UserInterface} class provides a text-based user interface for managing a virtual
 * fridge, shopping lists, and recipes. It allows users to interact with the application through a
 * console-based menu system to perform various operations such as adding or removing groceries from
 * a fridge or shopping list, managing recipes, and more.
 */

public class UserInterface {

  Scanner menuChoice = new Scanner(System.in);
  private UserInterfaceMethods uiMethod;
  private UserInterfaceRecipe uiRecipe;
  private boolean running;

  /**
   * Constructs a new {@code UserInterface} object. Initializes the scanner for reading user input
   * and creates instances for managing fridge, shopping list, and recipes.
   */

  public UserInterface() {
    this.uiMethod = new UserInterfaceMethods();
    this.uiRecipe = new UserInterfaceRecipe();
    running = true;
  }

  /**
   * Starts the text-based user interface. This method displays the main menu and processes user
   * input to navigate through the application's features including managing shopping lists, the
   * fridge, and recipes.
   */
  public void start() {
    uiMethod.initializeFridge();
    uiMethod.initializeShoppingList();
    uiRecipe.initializeRecipe();
    TextBasedUI.welcomeMessage();
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

  /**
   * Displays the recipes menu and allows the user to perform various operations on recipes such as
   * adding, removing, listing, and searching for recipes.
   */
  public void goToRecipes() {
    System.out.println("Recipes");
    while (running) {
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
          running = false;
          break;
        default:
          System.out.println("Invalid choice. Try again.");
          goToRecipes();
      }
    }
  }

  /**
   * Displays the fridge menu and allows the user to perform various operations on the fridge
   * content such as adding, removing groceries, listing items in the fridge, clearing the fridge,
   * and changing the quantity of an item.
   */

  public void goToFridge() {
    System.out.println("Fridge");
    while (running) {
      TextBasedUI.fridgePrint();
      switch (menuChoice.nextInt()) {
        case 1:
          uiMethod.addGroceryFridge();
          break;
        case 2:
          uiMethod.removeGroceryFridge();
          break;
        case 3:
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
          running = false;
          break;
        default:
          System.out.println("Invalid choice. Try again.");
          goToFridge();
      }
    }
  }

  /**
   * Displays the shopping list menu and allows the user to perform various operations on the
   * shopping list such as adding, removing groceries, printing the shopping list, clearing the
   * list, and changing the quantity of an item.
   */
  public void goToShoppingList() {
    System.out.println("Shopping list");
    TextBasedUI.shoppingListPrint();
    while (running) {
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
          running = false;
          break;
        default:
          System.out.println("Invalid choice. Try again.");
          goToShoppingList();
      }
    }
  }

  /**
   * Provides functionality to add groceries to the fridge directly from the shopping list. It
   * displays a submenu for the user to choose how to add groceries to the fridge.
   */
  private void addGroceryFridge() {
    while (running) {
      TextBasedUI.addGroceryToFridgeMenu();
      switch (menuChoice.nextInt()) {
        case 1:
          uiMethod.addOneGroceryFridge();
          break;
        case 2:
          uiMethod.addAllGroceryFridge();
          break;
        case 3:
          start();
          running = false;
          break;
        default:
          System.out.println("Invalid choice. Try again.");
          addGroceryFridge();
      }
    }

  }

  /**
   * Displays a drop-down menu for additional actions that can be performed. This method is a
   * placeholder and needs to be implemented to provide functionality for the drop-down menu
   * options.
   */
  public void goToDropDownMenu() {
    System.out.println("Drop down menu");
    TextBasedUI.dropDownPrint();
    while (running) {
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
          start();
          break;
        case 0:
          System.out.println("Goodbye!");
          running = false;
          break;
        default:
          System.out.println("Invalid choice. Try again.");
          goToDropDownMenu();
      }
    }
  }


}
