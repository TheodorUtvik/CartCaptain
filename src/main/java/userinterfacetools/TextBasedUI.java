package userinterfacetools;

import entities.FoodItem;
import entities.Recipe;
import java.sql.SQLOutput;

public class TextBasedUI {

  public static final String DAIRY = "Meieri";
  public static final String DRY = "Tørr";
  public static final String MEAT = "Kjøtt";
  public static final String BREAD = "Brød";
  public static final String FRUITANDGREEN = "Frukt & Grønt";
  public static final String SPICE = "Krydder";
  public static final String GRAIN = "Korn";
  public static final String SWEETS = "Søtsaker";
  public static final String DRINK = "Drikke";
  public static final String OTHER = "Annet";
  public static final String INVALIDCHOICE = "Invalid choice";
  public static final String KG = "kg";
  public static final String DL = "dl";
  public static final String STK = "stk";
  public static final String GRAM = "g";

  public static void printSelectFoodType() {
    System.out.println("Vennligst velg type for varen:");
    System.out.println("1. Meieri");
    System.out.println("2. Frukt & Grønt");
    System.out.println("3. Tørr");
    System.out.println("4. Kjøtt");
    System.out.println("5. Brød");
    System.out.println("6. Korn");
    System.out.println("7. Krydder");
    System.out.println("8. Søtsaker");
    System.out.println("9. Drikke");
    System.out.println("10. Annet");
  }

  public static void printSelectUnit() {
    System.out.println("Vennligst velg enhet for varen:");
    System.out.println("1. kg");
    System.out.println("2. dl");
    System.out.println("3. stk");
    System.out.println("4. g");
  }

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

  public static void inputNameToAdd() {
    System.out.println("Skriv inn navnet på varen du vil legge til i kjøleskapet:");
  }

  public static void inputNameDelete() {
    System.out.println("Skriv inn navnet på varen du vil fjerne:");
  }

  public static void inputQuantityMessage() {
    System.out.println("Skriv inn antall av varen du vil legge til:");
  }

  public static void inputUnitMessage() {
    System.out.println("Skriv inn enheten av varen du vil legge til:");
  }

  public static void itemMovedToFridge() {
    System.out.println("Varen er flyttet til kjøleskapet.");
  }

  public static void itemNotInList() {
    System.out.println("Varen er ikke i handlelisten.");
  }
  public static void addApproach() {
    System.out.println("Skriv inn fremgangsmåte:");
  }
  public static void addAnotherStep() {
    System.out.println("Vil du legge til et annet steg?");
    System.out.println("1. Ja");
    System.out.println("2. Nei");
  }
  public static void allItemsMovedToFridge() {
    System.out.println("Alle varene er flyttet til kjøleskapet.");
  }
  public static void addAnotherIngredient() {
    System.out.println("Vil du legge til en annen ingrediens?");
    System.out.println("1. Ja");
    System.out.println("2. Nei");
  }


  public static void inputQuantityChange() {
    System.out.println("Skriv inn det nye antallet av varen:");
  }

  public static void inputNameChange() {
    System.out.println("Skriv inn navnet på varen du vil endre:");
  }

  public static void clearFridgeMessage() {
    System.out.println("Kjøleskapet er tømt.");
  }

  public static void clearShoppingListMessage() {
    System.out.println("Handlelisten er tømt.");
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
    System.out.println("6. Legg til i kjøleskap og fjern fra handleliste");
    System.out.println("7. Hjem");
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
    System.out.println("4. Søk etter oppskrift");
    System.out.println("4. Hjem");
    System.out.println("0. Avslutt");
  }

  public static void addGroceryToFridgeMenu() {
    System.out.println("1. Legg til en vare i kjøleskapet");
    System.out.println("2. Legg til hele handlelisten i kjøleskapet");
    System.out.println("3. Hjem");
    //System.out.println("0. Avslutt");
  }

  public static void dropDownPrint() {
    System.out.println("1. Handleliste");
    System.out.println("2. Kjøleskap");
    System.out.println("3. Oppskrifter");
    System.out.println("4. Hjem");
    System.out.println("0. Avslutt");
  }

  public static void recipeName() {
    System.out.println("Enter recipe name:");
  }

  public static void timeToCook() {
    System.out.println("Enter time to cook:");
  }

  public static void cuisineType() {
    System.out.println("Enter cuisine type:");
  }

  public static void imgUrl() {
    System.out.println("Enter image URL:");
  }

  public static void recipeAdded() {
    System.out.println("Recipe added successfully!");
  }

  public static void recipeNameRemove() {
    System.out.println("Enter the name of the recipe to remove:");
  }

  public static void recipeRemoveSuccess() {
    System.out.println("Recipe removed successfully!");
  }

  public static void recipeNotFound() {
    System.out.println("Recipe not found.");
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

  public static String formatRecipe(Recipe recipe) {
    StringBuilder sb = new StringBuilder();
    sb.append("Oppskrift: ").append(recipe.getRecipeName()).append(" | ")
        .append("Tilberedningstid: ").append(recipe.getTimeToCookRecipe()).append(" | ")
        .append("Type: ").append(recipe.getCuisineType()).append(" | ")
        .append("Ingredienser: ").append(recipe.getIngredients()).append(" | ")
        .append("Fremgangsmåte: ").append(recipe.getApproach()).append(" | ")
        .append("Bilde: ").append(recipe.getImage());

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


