package Controllers;

import java.awt.PageAttributes.MediaType;
import java.awt.image.RescaleOp;
import java.util.List;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import Repositories.RecipeRepository;
import javax.servlet.http.HttpServletRequest;

@RestController
public class RecipeController {

	@Autowired
	RecipeRepository rr = new RecipeRepository();

	public String query = "";

	// this catches the /recipe/{id} request
	@RequestMapping(value = "/recipe/{id}", method = RequestMethod.GET)
	public String GetRecipeByID(@PathVariable("id") int id) {
		return rr.GetRecipeByID(id);
	}

	@RequestMapping(value = "/search/{searchvalue}", method = RequestMethod.GET)
	public String GetRecipeByID(@PathVariable("searchvalue") String searchvalue) {
		return rr.GetMatchingrecepies(searchvalue);
	}

	// deze methode zal worden opgeroepen wanneer er op de website op de knop
	// "filter" wordt geklikt het "*" zal dus gelijk welke objecten die worden
	// meegegeven in de link accepteren.

	@RequestMapping("/filter*")
	public String FilterRecipes(HttpServletRequest request) {
		String queryString = request.getQueryString();
		return Filter(queryString);
	}

	public String Filter(String queryString) {

		System.out.println("querystring= " + queryString);

		// deze worden delen van de Query
		// eerste deel
		String c = "Category_Category_ID=";

		// tweede deel
		String d = "";

		// derde deel
		String p = "";

		// vierde deel
		String t = "";

		// link splitten aan de "&"
		String[] splittedLink = queryString.split("&");

		// waarde die wordt aangepast als er een voorgaande waarde in de query
		// bestaat (voor de AND correct te plaatsen)
		boolean previousValue = false;

		/*
		 * CATEGORY_ID UIT DE LINK HALEN
		 */
		String[] cat = splittedLink[0].split("=");
		if (cat.length >= 2) {
			c += cat[1].replaceAll(",", " OR Category_Category_ID=");
			previousValue = true;
		}
		
		if(cat[1].equals("0")){
			c="";
			previousValue=false;
		}

		/*
		 * DIFFICULTY UIT DE LINK HALEN
		 */
		String[] dif = splittedLink[1].split("=");
		if (dif.length >= 2) {

			// checken of de category 0 is in de link (niet gekozen in de
			// filter)
			if (previousValue) {
				d += " AND Difficulty=";
			} else {
				c = "";
				d += " Difficulty=";
			}
			previousValue = true;

			d += dif[1].replaceAll(",", " OR Difficulty=");
		}
		if (dif[1].equals("0")) {
			d = "";
		}

		/*
		 * PRICE UIT DE LINK HALEN
		 */
		String[] pri = splittedLink[2].split("=");
		if (pri.length >= 2) {

			// checken of de difficulty 0 is in de link (niet gekozen in de filter)
			if (previousValue) {
				p += " AND Price=";
			} else {
				p += " Price=";
			}
			previousValue = true;

			p += pri[1].replaceAll(",", " OR Price=");
		}
		
		if (pri[1].equals("0")) {
			p = "";
		}

		/*
		 * TIME UIT DE LINK HALEN
		 */
		String[] tim = splittedLink[3].split("=");
		if (tim.length >= 2) {

			// checken of de prijs 0 is in de link (niet gekozen in de filter)
			if (previousValue) {
				t += " AND Time=";
			} else {
				d = "";
				t += " Time=";
			}
			previousValue = true;

			t += tim[1].replaceAll(",", " OR Time");
		}
		if (tim[1].equals("0")) {
			t = "";
		}

		// geen time gekozen
		if (t.equals(" Time=0") || t.equals(" AND Time=0")) {
			t = "";
		}

		// als niets gekozen is in de query
		if (c.equals("") && d.equals("") && p.equals("") && t.equals("")) {

			System.out.println("voor de return");
			return rr.FetchAllRecipes();

		} else {

			// querie maken
			query = "SELECT DISTINCT recipe_id, name, description FROM recipe "
					+ "JOIN recipe_has_category on recipe_has_category.Recipe_Recipe_ID = recipe.recipe_ID" + " WHERE "
					+ c + d + p + t + ";";

			// resultset in JSONformaat terug geven
			return rr.Filter(query);
		}
	}
}