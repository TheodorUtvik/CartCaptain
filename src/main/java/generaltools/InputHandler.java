package generaltools;

import java.util.Scanner;

public class InputHandler {

  private final Scanner userInput;

  public InputHandler() {
    this.userInput = new Scanner(System.in);
  }

  /**
   * Reads a string from the console. The method prints the prompt before reading the string.
   *
   * @return the string read from the console
   */
  public String readString() {
    return userInput.nextLine();
  }

  /**
   * Reads an integer from the console. The method prints the prompt before reading the integer. If
   * the input is not an integer, the method prints an error message and prompts the user to enter a
   * new value.
   * Inspired with stackoverflow.
   *
   * @return the integer read from the console
   */
  public int readInt() {
    while (!userInput.hasNextInt()) {
      userInput.next();
      System.out.print("Invalid input.");
    }
    int number = userInput.nextInt();
    userInput.nextLine();
    return number;
  }

}
