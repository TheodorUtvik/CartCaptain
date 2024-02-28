import entities.FoodItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Iterator;
import registers.FoodItemRegister;

import static org.junit.jupiter.api.Assertions.*;

class FoodItemRegisterTest {

  private FoodItemRegister register;
  private FoodItem foodItem1;
  private FoodItem foodItem2;

  @BeforeEach
  public void setUp() throws Exception {
    register = new FoodItemRegister();
    foodItem1 = new FoodItem("Milk", "Dairy", "L", 1);
    foodItem2 = new FoodItem("Bread", "Bread", "g", 500);

    register.addFoodItem(foodItem1);
    register.addFoodItem(foodItem2);
  }

  @Test
  void addFoodItem() {
    FoodItem foodItem3 = new FoodItem("Egg", "Egg", "pcs", 12);
    register.addFoodItem(foodItem3);

    assertEquals(foodItem3, register.findFoodItem("Egg"));
  }


 @Test
  void removeFoodItem() {
    register.removeFoodItem("Milk");
    assertNull(register.findFoodItem("Milk"));
  }


  @Test
  void findFoodItem() {
    assertEquals(foodItem1, register.findFoodItem("Milk"));
  }

  @Test
  void getItemsByType() {
    Iterator<FoodItem> iterator = register.GetItemsByType("Dairy");
    assertEquals(foodItem1, iterator.next());
  }
}