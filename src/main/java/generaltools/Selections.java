package generaltools;

import userinterfacetools.TextBasedUI;

public class Selections {


  public static String selectFoodType() {
    System.out.println("Please select the type of food item:");
    System.out.println("1. Meieri");
    System.out.println("2. Frukt");
    System.out.println("3. Grønnsaker");
    System.out.println("4. Kjøtt");
    System.out.println("5. Brød");
    System.out.println("6. Korn");
    System.out.println("7. Krydder");
    System.out.println("8. Søtsaker");
    System.out.println("9. Drikke");
    System.out.println("10. Annet");

    InputHandler inputHandler = new InputHandler();
    int selection = inputHandler.readInt();

    switch (selection) {
      case 1: return TextBasedUI.DAIRY;
      case 2: return TextBasedUI.FRUIT;
      case 3: return TextBasedUI.VEGETABLE;
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
    System.out.println("Please select the unit of the food item:");
    System.out.println("1. kg");
    System.out.println("2. dl");
    System.out.println("3. stk");
    System.out.println("4. g");

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

}
