package main.najah.test;

import main.najah.code.Recipe;
import main.najah.code.RecipeBook;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("RecipeBook Tests")
public class TestRecipeBook {
    RecipeBook Book;
    Recipe Recipe1,Recipe2;
    @BeforeEach
    void setUp()
    {
        Book = new RecipeBook();
        Recipe1 = new Recipe();
        Recipe2 = new Recipe();
        Recipe1.setName("coffee");
        Recipe2.setName("tea");
    }
    @Test
    @DisplayName("Add recipe test")
    void testAddRecipe()
    {
        assertAll("Add recipe",
                ()->assertTrue(Book.addRecipe(Recipe1)),
                ()->assertTrue(Book.addRecipe(Recipe2)),
                ()->assertFalse(Book.addRecipe(Recipe1)));
    }
    @Test
    @DisplayName("Delete recipe test")
    void testDeleteRrecipe()
    {
        Book.addRecipe(Recipe1);
        String delete=Book.deleteRecipe(0);
        assertEquals("coffee",delete);
        assertEquals("", Book.getRecipes()[0].getName());
    }
    @Test

    @DisplayName("Edit recipe test")
    void testEditRecipe() {
        Book.addRecipe(Recipe1);
        String oldName = Book.editRecipe(0, Recipe2);
        assertEquals("coffee", oldName.toLowerCase());
        assertEquals("", Book.getRecipes()[0].getName());
    }
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    @DisplayName("Add multiple recipes parametrized")
    void testAddMultipleRecipes(int index) {
        Recipe r = new Recipe();
        r.setName("Recipe" + index);
        assertTrue(Book.addRecipe(r));
    }

    @Test
    @Timeout(1)
    @DisplayName("Timeout test for addRecipe")
    void testTimeout() {
        Book.addRecipe(Recipe1);
        assertEquals(4, Book.getRecipes().length);
    }

    @Test
    @Disabled("Intentionally failing test ")
    @DisplayName("Disabled failing test")
    void failingTest() {
        Book.deleteRecipe(1);
        assertEquals("NonExisting", Book.deleteRecipe(1));
    }












}
