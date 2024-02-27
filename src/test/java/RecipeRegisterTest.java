import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeRegisterTest {

    private RecipeRegister register;
    private Recipe recipe1;
    private Recipe recipe2;

    @BeforeEach
    public void setUp() throws Exception {
      register = new RecipeRegister();
      recipe1 = new Recipe("Pancakes", "1 hour", "test1", 10);
      recipe2 = new Recipe("Pizza", "2 hours", "test2", 20);

      register.addRecipe(recipe1);
      register.addRecipe(recipe2);
    }

    @Test
    void addRecipe() {
      Recipe recipe3 = new Recipe("Pasta", "30 minutes", "test3", 30);
      register.addRecipe(recipe3);

      assertEquals("recipe3", register.findRecipe("Pasta"));
    }

    @Test
    void removeRecipe() {
      register.removeRecipe("Pancakes");
      assertNull("Recipe removed", register.findRecipe("Pancakes"));
    }

    @Test
    void findRecipe() {
      assertEquals("recipe1", register.findRecipe("Pancakes"));
    }

    @Test
    void getRecipesByType() {
      Iterator<Recipe> iterator = register.GetRecipesByType("Dinner");
      assertEquals("recipe2", iterator.next());
    }

}
