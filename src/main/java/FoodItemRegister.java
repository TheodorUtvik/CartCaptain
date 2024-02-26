import java.util.HashMap;
import java.util.Iterator;

public class FoodItemRegister {

  private HashMap<String, FoodItem> register;

  public FoodItemRegister() {
    this.register = new HashMap<>();
  }

  public void addFoodItem(FoodItem foodItem) {
    this.register.put(foodItem.getName(), foodItem);
  }

  public void removeFoodItem(String name) {
    this.register.remove(name);
  }

  public FoodItem findFoodItem(String name) {
    return this.register.get(name);
  }

  public Iterator<FoodItem> GetItemsByType(String type) {
    return this.register.values().stream().filter(item -> item.getFoodType().equals(type))
        .iterator();
  }

}
