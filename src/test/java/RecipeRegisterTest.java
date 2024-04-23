import entities.Recipe;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Iterator;
import registers.RecipeRegister;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for RecipeRegister. The class tests the following methods:
 * <li>addRecipe</li>
 * <li>removeRecipe</li>
 * <li>findRecipe</li>
 * <li>getRecipesByType</li>
 *
 * @author Simon Snildal, Halvard Nordberg, Theodor Sjetnan Utvik
 * @see RecipeRegister
 * @see Recipe
 * @since 27.02.2024
 */
class RecipeRegisterTest {

  private RecipeRegister register;
  private Recipe recipe1;
  private Recipe recipe2;

  /**
   * Sets up the test environment by creating a new RecipeRegister object and adding two recipes to
   * it.
   */
  @BeforeEach
  public void setUp() {
    ArrayList<String> ingredients1 = new ArrayList<>();
    ArrayList<String> ingredients2 = new ArrayList<>();

    ArrayList<String> approach1 = new ArrayList<>();
    ArrayList<String> approach2 = new ArrayList<>();

    register = new RecipeRegister();
    recipe1 = new Recipe("Pancakes", "1 hour",
        "test1", ingredients1, approach1, "test1");
    recipe2 = new Recipe("Pizza", "2 hours",
        "test2", ingredients2, approach2, "test2");

    register.tryAddRecipe(recipe1);
    register.tryAddRecipe(recipe2);
  }

  /**
   * A recipe is added to the register. The method then checks to assert if the recipe was
   * successfully added to the register, and that the register is able to find the right recipe.
   */
  @Test
  void addRecipe() {
    Recipe recipe3 = new Recipe("Pasta", "30 minutes", "test3", new ArrayList<>(),
        new ArrayList<>(), "test3");
    register.tryAddRecipe(recipe3);

    assertEquals(recipe3, register.findRecipe("Pasta"));
  }

  /**
   * A recipe is removed from the register. The method then checks to assert if the recipe was
   * successfully removed from the register.
   */
  @Test
  void removeRecipe() {
    register.removeRecipe("Pancakes");
    assertNull(register.findRecipe("Pancakes"));
  }

  /**
   * The method checks to assert if the register is able to find the right recipe.
   */
  @Test
  void findRecipe() {
    assertEquals(recipe1, register.findRecipe("Pancakes"));
  }

  /**
   * The method checks to assert if the register is able to find the right recipes by type.
   */
  @Test
  void getRecipesByType() {
    Iterator<Recipe> iterator1 = register.GetRecipesByType("test2");
    assertEquals(recipe2, iterator1.next());
  }

}
