package generaltools;

import static userinterfacetools.TextBasedUI.printSelectCuisineType;
import static userinterfacetools.TextBasedUI.printSelectFoodType;
import static userinterfacetools.TextBasedUI.printSelectUnit;

import userinterfacetools.TextBasedUI;

public class Selections {


  public static String selectFoodType() {
    printSelectFoodType();

    InputHandler inputHandler = new InputHandler();
    int selection = inputHandler.readInt();

    switch (selection) {
      case 1: return TextBasedUI.DAIRY;
      case 2: return TextBasedUI.FRUITANDGREEN;
      case 3: return TextBasedUI.DRY;
      case 4: return TextBasedUI.MEAT;
      case 5: return TextBasedUI.BREAD;
      case 6: return TextBasedUI.GRAIN;
      case 7: return TextBasedUI.SPICE;
      case 8: return TextBasedUI.SWEETS;
      case 9: return TextBasedUI.DRINK;
      case 10: return TextBasedUI.OTHER;
      default: return TextBasedUI.INVALIDCHOICE;
    }
  }

  public static String selectUnit() {
    printSelectUnit();

    InputHandler inputHandler = new InputHandler();
    int selection = inputHandler.readInt();

    switch (selection) {
      case 1: return TextBasedUI.KG;
      case 2: return TextBasedUI.DL;
      case 3: return TextBasedUI.STK;
      case 4: return TextBasedUI.GRAM;
      default: return TextBasedUI.INVALIDCHOICE;
    }
  }

  public static String selectCuisineType() {
    printSelectCuisineType();

    InputHandler inputHandler = new InputHandler();
    int selection = inputHandler.readInt();

    switch (selection) {
      case 1: return TextBasedUI.FISH;
      case 2: return TextBasedUI.MEATTYPE;
      case 3: return TextBasedUI.VEGETARIAN;
      case 4: return TextBasedUI.VEGAN;
      case 5: return TextBasedUI.PASTA;
      case 6: return TextBasedUI.SOUP;
      case 7: return TextBasedUI.SALAD;
      case 8: return TextBasedUI.CAKE;
      case 9: return TextBasedUI.BREAKFAST;
      case 10: return TextBasedUI.OTHER;
      default: return TextBasedUI.INVALIDCHOICE;
    }
  }

}
