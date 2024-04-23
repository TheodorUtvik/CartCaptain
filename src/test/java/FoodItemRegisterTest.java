import entities.FoodItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Iterator;
import registers.FoodItemRegister;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for FoodItemRegister. The class tests the following methods:
 * <li>addFoodItem</li>
 * <li>removeFoodItem</li>
 * <li>findFoodItem</li>
 * <li>getItemsByType</li>
 *
 * @author Simon Snildal, Halvard Nordberg, Theodor Sjetnan Utvik, Sigurd Riseth
 * @see FoodItemRegister
 * @see FoodItem
 * @since 26.02.2024
 */
class FoodItemRegisterTest {

  private FoodItemRegister register;
  private FoodItem foodItem1;
  private FoodItem foodItem2;

  /**
   * Sets up the test environment by creating a new FoodItemRegister object and adding two food
   * items to it. The food items are a milk carton and a loaf of bread.
   */
  @BeforeEach
  public void setUp() {
    register = new FoodItemRegister();
    foodItem1 = new FoodItem("Milk", "Dairy", "L", 1);
    foodItem2 = new FoodItem("Bread", "Bread", "g", 500);

    register.tryAddFoodItem(foodItem1);
    register.tryAddFoodItem(foodItem2);
  }

  /**
   * A food item is added to the register. The method then checks to assert if the food item was
   * successfully added to the register, and that the register is able to find the right foodType.
   */
  @Test
  void addFoodItem() {
    FoodItem foodItem3 = new FoodItem("Egg", "Egg", "pcs", 12);
    register.tryAddFoodItem(foodItem3);

    assertEquals(foodItem3, register.findFoodItem("Egg"));
  }


  /**
   * A food item is removed from the register. The method then checks to assert if the food item was
   * successfully removed from the register.
   */
  @Test
  void removeFoodItem() {
    register.removeFoodItem("Milk");
    assertNull(register.findFoodItem("Milk"));
  }


  /**
   * The method checks to assert if the register is able to find the right food item.
   */
  @Test
  void findFoodItem() {
    assertEquals(foodItem1, register.findFoodItem("Milk"));
  }

  /**
   * The method checks to assert if the register is able to find the right food items by type.
   */
  @Test
  void getItemsByType() {
    Iterator<FoodItem> iterator = register.GetItemsByType("Dairy");
    assertEquals(foodItem1, iterator.next());
  }
}