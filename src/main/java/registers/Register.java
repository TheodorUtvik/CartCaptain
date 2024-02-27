package registers;

import java.util.HashMap;
import java.util.Iterator;
import entities.FoodItem;

/**
 * Class for storing FoodItems.
 *
 * @author Sigurd Riseth
 * @version 0.0.2
 * @see FoodItem
 * @since 26.02.2024
 */
public class Register {

  /**
   * The register of food items.
   */
  private final HashMap<String, FoodItem> register;

  /**
   * Constructs a new empty FoodItemRegister.
   */
  public Register() {
    this.register = new HashMap<>();
  }

  /**
   * Adds a food item to the register.
   *
   * @param foodItem the food item to add
   */
  public void addFoodItem(FoodItem foodItem) {
    this.register.put(foodItem.getName(), foodItem);
  }

  /**
   * Removes a food item from the register.
   *
   * @param name the name of the food item to remove
   */
  public void removeFoodItem(String name) {
    this.register.remove(name);
  }

  /**
   * Returns a food item in the register by name.
   *
   * @param name the name of the food item to find
   * @return the food item with the specified name
   */
  public FoodItem findFoodItem(String name) {
    return this.register.get(name);
  }

  /**
   * Returns an iterator of food items in the register by type.
   *
   * @param type the type of the food items to find
   * @return an iterator of food items with the specified type
   */
  public Iterator<FoodItem> GetItemsByType(String type) {
    return this.register.values().stream().filter(item -> item.getFoodType().equals(type))
        .iterator();
  }

}
