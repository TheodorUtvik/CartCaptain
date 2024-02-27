package entities;

/**
 * Represents a food item with a name, quantity, and unit.
 *
 * @author Simon Snildal
 * @version 0.0.1
 * @since 26.02.2024
 */
public class FoodItem {

  /**
   * The name of the food item.
   */
  private String name;
  /**
   * The unit of the food item.
   */
  private String unit;
  /**
   * The type of the food item.
   */
  private String foodType;
  /**
   * The quantity of the food item.
   */
  private int quantity;

  /**
   * Constructs a new FoodItem with the specified name, quantity, and unit.
   *
   * @param name     the name of the food item
   * @param quantity the quantity of the food item
   * @param unit     the unit of the food item
   * @param foodType the type of the food item
   */
  public FoodItem(String name, String foodType, String unit, int quantity) {
    this.name = name;
    this.foodType = foodType;
    this.unit = unit;
    this.quantity = quantity;
  }

  // Getters and setters

  /**
   * Returns the name of the food item.
   *
   * @return the name of the food item
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the food item.
   *
   * @param name the new name of the food item
   */

  public void setName(String name) {
    this.name = name;
  }

  /**
   * Returns the type of the food item.
   *
   * @return the type of the food item
   */
  public String getFoodType() {
    return foodType;
  }

  /**
   * Sets the type of the food item.
   *
   * @param foodType the new type of the food item
   */
  public void setFoodType(String foodType) {
    this.foodType = foodType;
  }

  /**
   * Returns the unit of the food item.
   *
   * @return the unit of the food item
   */
  public String getUnit() {
    return unit;
  }

  /**
   * Sets the unit of the food item.
   *
   * @param unit the new unit of the food item
   */

  public void setUnit(String unit) {
    this.unit = unit;
  }

  /**
   * Returns the quantity of the food item.
   *
   * @return the quantity of the food item
   */

  public int getQuantity() {
    return quantity;
  }

  /**
   * Sets the quantity of the food item.
   *
   * @param quantity the new quantity of the food item
   */

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  /**
   * Prints the name, quantity, and unit of the food item.
   */

  public void printInfo() {
    System.out.println(
        "Name: " + name + ", Quantity: " + quantity + ", Unit: " + unit + ", Type: " + foodType);
  }
}
