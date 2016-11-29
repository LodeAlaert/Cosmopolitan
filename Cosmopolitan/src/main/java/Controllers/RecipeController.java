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

import Controllers.MainController;

@RestController
public class RecipeController {

	@Autowired
	RecipeRepository rr;

	// this catches the /recipe/{id} request
	@RequestMapping(value = "/recipe/{id}", method = RequestMethod.GET)
	public String GetRecipeByID(@PathVariable("id") int id) {
		return rr.GetRecipeByID(id);
	}

	// deze methode zal worden opgeroepen wanneer er op de website op de knop
	// "filter" wordt geklikt het "*" zal dus gelijk welke objecten die worden
	// meegegeven in de link accepteren.
	@RequestMapping("/filter*")
	public String FilterRecipes(HttpServletRequest request) {

		// dit is de volledige link waar alles uit gehaald kan worden: na
		// "filter?"
		String queryString = request.getQueryString();

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

		/*
		 * CATEGORY_ID UIT DE LINK HALEN
		 */
		String[] cat = splittedLink[0].split("=");
		if (cat.length >= 2) {
			c += cat[1].replaceAll(",", " OR Category_Category_ID=");
		}

		/*
		 * DIFFICULTY UIT DE LINK HALEN
		 */
		String[] dif = splittedLink[1].split("=");
		if (dif.length >= 2) {

			// checken of de category 0 is in de link (niet gekozen in de
			// filter)
			if (c.equals("Category_Category_ID=0")) {

				// geen category gekozen
				c = "";
				d += " Difficulty=";
			} else {
				d += " AND Difficulty=";
			}
			d += dif[1].replaceAll(",", " OR Difficulty=");
		}

		/*
		 * PRICE UIT DE LINK HALEN
		 */
		String[] pri = splittedLink[2].split("=");
		if (pri.length >= 2) {

			// checken of de difficulty 0 is in de link (niet gekozen in de
			// filter)
			if (d.equals(" Difficulty=0") || d.equals(" AND Difficulty=0")) {

				// geen difficulty gekozen
				d = "";
				p += " Price=";
			} else {
				p += " AND Price=";
			}
			p += pri[1].replaceAll(",", " OR Price=");
		}

		/*
		 * TIME UIT DE LINK HALEN
		 */
		String[] tim = splittedLink[3].split("=");
		if (tim.length >= 2) {

			// checken of de prijs 0 is in de link (niet gekozen in de filter)
			if (p.equals(" Price=0") || p.equals(" AND Price=0")) {

				// geen prijs gekozen
				p = "";
				t += " time=";
			} else {
				t += " AND time=";
			}
			t += tim[1].replaceAll(",", " OR Time");
		}

		// geen time gekozen
		if (t.equals(" time=0") || t.equals(" AND time=0")) {
			t = "";
		}

		if (c.equals("") && d.equals("") && p.equals("") && t.equals("")) {

			return rr.FetchAllRecipes();
			
		} else {

			// querie maken
			String query = "SELECT DISTINCT recipe_id, name, description FROM recipe "
					+ "JOIN recipe_has_category on recipe_has_category.Recipe_Recipe_ID = recipe.recipe_ID" + " WHERE "
					+ c + d + p + t + ";";

			// resultset in JSONformaat terug geven
			return rr.Filter(query);
		}
	}
}
