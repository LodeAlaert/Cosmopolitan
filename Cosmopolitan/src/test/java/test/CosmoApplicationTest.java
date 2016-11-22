package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import Models.Category;
import Models.Ingredient;
import Models.Recipe;
import Repositories.CategoryRepository;
import Repositories.RecipeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class CosmoApplicationTest {

	private final Category c1, c2;
	private final Recipe r1;
	private final Ingredient i1, i2;
	
	RecipeRepository rr = new RecipeRepository();
	CategoryRepository cr = new CategoryRepository();	

	//test 
	public CosmoApplicationTest() {
		this.c1 = new Category(1, "Vlees");
		this.c2 = new Category(2, "Vis");
		this.r1 = new Recipe(1, "Balletjes in tomatensaus", 1, 4, "dit een een beschrijving",
				"dit is het recept, 1. steek het in de microgolf 2. eet het op", "5", 30);
		this.i1 = new Ingredient(1, "witte wijn", "cl");
		this.i2 = new Ingredient(1, "tomaat", "stuk");
	}

	// test if the categories can be fetched from the DB
	@Test
	public void TestFetchCategories() {
		String test = cr.GetAllCategories();
	}

	// test if the recipe with the right ID is fetched from the DB
	@Test
	public void TestFetchRecipeByID(){
		String str = "[{\"Description\":\"Een recept van Piet Huysentruyt waarin hij een heerlijke spaghetti maakt van gerookte asperges en zalm\",\"Recipe\":\"1. Rook de zalm in het rookbakje of in de wok (facultatief).\",\"Price\":\"20\",\"Difficulty\":\"1\",\"Persons\":\"4\",\"Time\":\"20\",\"Recipe_ID\":\"2\",\"Name\":\"Gegrilde gerookte zalm met spaghetti van asperges\"}]";
		assertEquals(str, rr.GetRecipeByID(2));
	}
	
	// test if all the recipes can be fetched
	@Test
	public void TestFetchAllCategories(){
		assertNotNull(rr.FetchAllRecipes());
	}
	
	// test the getters & setters of recipe
	@Test
	public void TestGSRecipe(){
		r1.setRecipe_id(2);
		r1.setName("name");
		r1.setDifficulty(1);
		r1.setPersons(2);
		r1.setDescription("description");
		r1.setRecipe("recipe");
		r1.setPrice("500");
		r1.setTime(25);
		
		assertEquals(2, r1.getRecipe_id());
		assertEquals("name", r1.getName());
		assertEquals(1, r1.getDifficulty());
		assertEquals(2, r1.getPersons());
		assertEquals("description", r1.getDescription());
		assertEquals("recipe", r1.getRecipe());
		assertEquals("500", r1.getPrice());
		assertEquals(25, r1.getTime());
	}
	
	@Test
	public void TestGSCategory(){
		c1.setCategory_ID(3);
		c1.setName("spaghetti");
		
		assertEquals(3,  c1.getCategory_ID());
		assertEquals("spaghetti", c1.getName());
	}
	
	@Test
	public void TestGSIngredient(){
		i1.setIngredient_ID(3);
		i1.setName("look");
		i1.setUnit("teentjes");
		
		assertEquals(3, i1.getIngredient_ID());
		assertEquals("look", i1.getName());
		assertEquals("teentjes", i1.getUnit());
	}
}
