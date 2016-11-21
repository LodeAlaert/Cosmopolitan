package test;

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

@RunWith(SpringJUnit4ClassRunner.class)
public class CosmoApplicationTest {

	@Test
	public void contextLoads() {
	}

	@Test
	public void nogEenTestMethode() {

	}

	@Test
	public void TestCategory() {
		Category c1 = new Category(1, "Vlees");
		Category c2 = new Category(2, "Vis");
	}

	@Test
	public void TestRecipe() {
		Recipe r1 = new Recipe(1, "Balletjes in tomatensaus", 1, 4, "dit een een beschrijving",
				"dit is het recept, 1. steek het in de microgolf 2. eet het op", "5", 30);
	}

	@Test
	public void TestIngredient() {
		Ingredient i1 = new Ingredient(1, "witte wijn", "cl");
	}
	
	@Test
	public void TestFetchCategories(){
		CategoryRepository cr = new CategoryRepository();
		String test = cr.GetAllCategories();
	}
}
