package registers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import entities.FoodItem;
import java.util.List;
import java.util.Map;

/**
 * Class for storing FoodItems.
 *
 * @author Sigurd Riseth
 * @version 0.0.2
 * @see FoodItem
 * @since 26.02.2024
 */
public class FoodItemRegister {

  /**
   * The register of food items.
   */
  private final HashMap<String, FoodItem> register;

  /**
   * Constructs a new empty FoodItemRegister.
   */
  public FoodItemRegister() {
    this.register = new HashMap<>();
  }

  /**
   * Adds a food item to the register which includes:
   * <li>name</li>
   * <li>foodType</li>
   * <li>unit</li>
   * <li>quantity</li>
   *
   * @param foodItem the name of the food item
   */
  public boolean tryAddFoodItem(FoodItem foodItem) {
    if(register.containsKey(foodItem.getName())) {
      return false;
    }
    this.register.put(foodItem.getName(), foodItem);
    return true;
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
    if (name == null) {
      return null; // or throw an exception
    }
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

  public Iterator<FoodItem> getFoodItems() {
    return register.values().iterator();
  }


  FoodItem foodItem = new FoodItem("Milk", "Dairy", "L", 1);
  FoodItem foodItem2 = new FoodItem("Egg", "Dairy", "pcs", 12);
  FoodItem foodItem3 = new FoodItem("Bread", "Bread", "pcs", 1);
  FoodItem foodItem4 = new FoodItem("Butter", "Dairy", "g", 250);
  FoodItem foodItem5 = new FoodItem("Cheese", "Dairy", "g", 500);


  public void initialize(){
    tryAddFoodItem(foodItem);
    tryAddFoodItem(foodItem2);
    tryAddFoodItem(foodItem3);
    tryAddFoodItem(foodItem4);
    tryAddFoodItem(foodItem5);
  }
  public List<FoodItem> removeAllItems() {
    List<FoodItem> removedItems = new ArrayList<>(register.values());
    register.clear();
    return removedItems;
  }
}

