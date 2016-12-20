package test;

import Controllers.MainController;
import Controllers.RecipeController;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
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
import Repositories.IngredientsRepository;
import Repositories.RecipeRepository;
import Repositories.ResultSetToJSONConverter;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpUpgradeHandler;
import javax.servlet.http.Part;

import org.json.JSONObject;

import org.springframework.mock.web.MockHttpServletRequest;
import Controllers.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class CosmoApplicationTest {

	private final Category c1;
	private final Recipe r1;
	private final Ingredient i1;
	private String main="";
	private String result="";
	
	RecipeRepository rr;
	CategoryRepository cr;
	IngredientsRepository ir;
	RecipeController rc;
	IngredientController ic;
	MainController mc;
	
	
	@Before
	public void SetUp(){
		main="";
		result="";
		
		rr = new RecipeRepository();
		cr = new CategoryRepository();
		rc = new RecipeController();
		ic = new IngredientController();
		mc = new MainController();
		ir = new IngredientsRepository();
	}




	public CosmoApplicationTest() {
		this.c1 = new Category(1, "Vlees");
		this.r1 = new Recipe(1, "Balletjes in tomatensaus", 1, 4, "dit een een beschrijving",
				"dit is het recept, 1. steek het in de microgolf 2. eet het op", "5", 30);
		this.i1 = new Ingredient(1, "witte wijn", "cl");
	}

	// test if the categories can be fetched from the DB
	@Test
	public void TestFetchCategories() {
		String test = cr.GetAllCategories();
	}
	
	// test the filter (alles leeg)
	@Test
	public void TestFilterRecipes() {
		String recipe = "dit is niet null";
		recipe = rc.Filter("category=0&difficulty=0&price=0&time=0");
		assertNotNull(recipe);
	}
	
	// test the filter (iets ingevuld)
	@Test
	public void TestFilterRecipes2() {
		String recipe = "dit is niet null";
		recipe = rc.Filter("category=0&difficulty=1&price=0&time=0");
		assertNotNull(recipe);
	}

	// test the getters & setters of recipe
	@Test
	public void TestGSRecipe() {
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
	public void TestGSCategory() {
		c1.setCategory_ID(3);
		c1.setName("spaghetti");

		assertEquals(3, c1.getCategory_ID());
		assertEquals("spaghetti", c1.getName());
	}

	@Test
	public void TestGSIngredient() {
		i1.setIngredient_ID(3);
		i1.setName("look");
		i1.setUnit("teentjes");

		assertEquals(3, i1.getIngredient_ID());
		assertEquals("look", i1.getName());
		assertEquals("teentjes", i1.getUnit());
	}
	
	@Test //(expected=NullPointerException.class)
	public void TestIngredients(){
		assertNotNull(ir.GetIngredientsByRecipeID(1));
	}
	
	/*
	@Test //(expected=NullPointerException.class)
	public void TestMainController(){
		assertNotNull(mc.GetAllRecipes());
	}
	*/
	
	// test if all the recipes can be fetched
	@Test
	public void TestFetchAllCategories() {
		assertNotNull(rr.FetchAllRecipes());
	}
	
	@Test
	public void TestSearchfunction(){
		assertNotNull(rr.GetMatchingrecepies("pompoen"));
	}
	
	@Test
	public void TestRecipeByID(){
		int id = 1;
		
		String recipe = rr.GetRecipeByID(id);
		assertNotNull(recipe);
	}
	
	@Test 
	public void TestFilterRepository(){
		String query = "SELECT DISTINCT recipe_id, name, description FROM recipe JOIN recipe_has_category on recipe_has_category.Recipe_Recipe_ID = recipe.recipe_ID WHERE Category_Category_ID=1 OR Category_Category_ID=2 AND Difficulty=1";
		String recipes = rr.Filter(query);
		assertNotNull(recipes);
	}
	
	
	@Test
	public void TestJSON(){
		ResultSet rs = null;
		ResultSetToJSONConverter rstjc = new ResultSetToJSONConverter();
		assertNotNull(rstjc);
	}
}
