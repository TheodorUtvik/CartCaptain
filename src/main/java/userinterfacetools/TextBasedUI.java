package userinterfacetools;

import entities.FoodItem;

public class TextBasedUI {

  public static void welcomeMessage() {
    System.out.println("Velkommen til dagligvareappen!");
  }

  public static void notInList() {
    System.out.println("Varen er ikke i kjøleskapet.");
  }

  public static void groceryRemoved() {
    System.out.println("Vare fjernet fra kjøleskapet.");
  }

  public static void existsInList() {
    System.out.println("Varen er allerede i kjøleskapet.");
  }

  public static void groceryAdded() {
    System.out.println("Vare lagt til i matbeholdningen.");
  }

  public static void inputNameMessage() {
    System.out.println("Skriv inn navnet på varen du vil legge til:");
  }

  public static void inputNameDelete() {
    System.out.println("Skriv inn navnet på varen du vil fjerne:");
  }

  public static void inputQuantityMessage() {
    System.out.println("Skriv inn antall av varen du vil legge til:");
  }

  public static void inputTypeMessage() {
    System.out.println("Skriv inn typen av varen du vil legge til:");
  }

  public static void inputUnitMessage() {
    System.out.println("Skriv inn enheten av varen du vil legge til:");
  }

  public static void inputQuantityChange() {
    System.out.println("Skriv inn det nye antallet av varen:");
  }

  public static void inputNameChange() {
    System.out.println("Skriv inn navnet på varen du vil endre:");
  }

  public static void menuPrint() {
    System.out.println("1. Handleliste");
    System.out.println("2. Kjøleskap");
    System.out.println("3. Oppskrifter");
    System.out.println("4. Rullgardin");
    System.out.println("5. Hjem");
    System.out.println("0. Avslutt");
  }

  public static void shoppingListPrint() {
    System.out.println("1. Legg til vare");
    System.out.println("2. Fjern vare");
    System.out.println("3. Skriv ut handleliste");
    System.out.println("4. Tøm handleliste");
    System.out.println("5. Endre antall");
    System.out.println("6. Hjem");
    System.out.println("0. Avslutt");
  }

  public static void fridgePrint() {
    System.out.println("1. Legg til vare");
    System.out.println("2. Fjern vare");
    System.out.println("3. Skriv ut kjøleskap");
    System.out.println("4. Tøm kjøleskap");
    System.out.println("5. Endre antall");
    System.out.println("6. Hjem");
    System.out.println("0. Avslutt");
  }

  public static void recipePrint() {
    System.out.println("1. Legg til oppskrift");
    System.out.println("2. Fjern oppskrift");
    System.out.println("3. Skriv ut oppskrifter");
    System.out.println("4. Hjem");
    System.out.println("0. Avslutt");
  }

  public static void dropDownPrint() {
    System.out.println("1. Handleliste");
    System.out.println("2. Kjøleskap");
    System.out.println("3. Oppskrifter");
    System.out.println("4. Hjem");
    System.out.println("0. Avslutt");
  }

  public static String formatGrocery(FoodItem foodItem) {
    StringBuilder sb = new StringBuilder();
    sb.append("Matvare: ").append(foodItem.getName()).append(" | ")
        .append("Type: ").append(foodItem.getFoodType()).append(" | ")
        .append("Enhet: ").append(foodItem.getUnit()).append(" | ")
        .append("Antall: ").append(foodItem.getQuantity());

    String decLine = "\n" + "-".repeat(sb.length());
    sb.append(decLine);

    return sb.toString();
  }


  public static String formatGroceryHeader() {
      final String ANSI_BOLD = "\033[1m";
      final String ANSI_RESET = "\033[0m";
      final String ANSI_BLUE = "\033[34m";

      // Create the header string
      String header = ANSI_BOLD + ANSI_BLUE + "Matvare | Mat type | Enhet | Antall" + ANSI_RESET;

      // Calculate the length of the header string without ANSI escape codes for accurate repetition
      String headerWithoutAnsi = "Matvare | Mat type | Enhet | Antall";

      // Create the decorative line
      String decLine = "\n" + ANSI_BLUE + "-".repeat(headerWithoutAnsi.length()) + ANSI_RESET;

      // Return the combined header and decorative line
      return header + decLine;
    }

  }


