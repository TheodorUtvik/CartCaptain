import entities.FoodItem;
import entities.Recipe;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Iterator;
import registers.RecipeRegister;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeRegisterTest {

    private RecipeRegister register;
    private Recipe recipe1;
    private Recipe recipe2;

    @BeforeEach
    public void setUp() throws Exception {
      ArrayList<FoodItem> ingredients1 = new ArrayList<>();
      ArrayList<FoodItem> ingredients2 = new ArrayList<>();

      ArrayList<String> approach1 = new ArrayList<>();
      ArrayList<String> approach2 = new ArrayList<>();

      register = new RecipeRegister();
      recipe1 = new Recipe("Pancakes", "1 hour", "test1", ingredients1, approach1, "test1");
      recipe2 = new Recipe("Pizza", "2 hours", "test2", ingredients2, approach2, "test2");

      register.tryAddRecipe(recipe1);
      register.tryAddRecipe(recipe2);
    }

    @Test
    void addRecipe() {
      Recipe recipe3 = new Recipe("Pasta", "30 minutes", "test3", new ArrayList<>(), new ArrayList<>(), "test3");
      register.tryAddRecipe(recipe3);

      assertEquals(recipe3, register.findRecipe("Pasta"));
    }

    @Test
    void removeRecipe() {
      register.removeRecipe("Pancakes");
      assertNull(register.findRecipe("Pancakes"));
    }



    @Test
    void findRecipe() {
      assertEquals(recipe1, register.findRecipe("Pancakes"));
    }

    @Test
    void getRecipesByType() {
      Iterator<Recipe> iterator1 = register.GetRecipesByType("test2");
      assertEquals(recipe2, iterator1.next());
    }

}
