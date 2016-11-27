package Controllers;

import java.awt.PageAttributes.MediaType;
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
	RecipeRepository rr;

	// this catches the /recipe/{id} request
	@RequestMapping(value = "/recipe/{id}", method = RequestMethod.GET)
	public String GetRecipeByID(@PathVariable("id") int id) {
		return rr.GetRecipeByID(id);
	}

	// deze methode zal worden opgeroepen wanneer er op de website op de knop
	// "filter" wordt geklikt
	// het "*" zal dus gelijk welke objecten die worden meegegeven in de link
	// accepteren.
	@RequestMapping("/filter*")
	public String FilterRecipes(HttpServletRequest request) {

		// check dees: https://gyazo.com/490afaa45db60caf014531e6877fdc75
		// zo zal de query er uiteindelijk uit zien.

		// hier wordt een deeltje van die link eruit gehaald, de link zal worden
		// opgesplitst in categorie, moeilijkheid, prijs en bereidingstijd
		// als deze voorkomen in de link zullen deze waarden gebruikt worden
		// om (in de repository) een query samen te stellen om de gewenste
		// recepten
		// op te halen uit de DB

		// dit is de volledige link waar alles uit gehaald kan worden:
		String queryString = request.getQueryString();

		// deze worden delen van de Query
		String c = "Category_Category_ID=";
		String d = "";
		String p = "";
		String t = "";

		// link splitten aan de "&"
		String[] splittedLink = queryString.split("&");

		/*
		 * CATEGORY_ID UIT DE LINK HALEN
		 */
		String[] cat = splittedLink[0].split("=");
		if (cat.length >= 2) {
			c += cat[1].replaceAll(",", " OR ");
		}

		/*
		 * DIFFICULTY UIT DE LINK HALEN
		 */
		String[] dif = splittedLink[1].split("=");
		if (dif.length >= 2) {

			if (c.equals("Category_Category_ID=0")) {

				// geen category gekozen
				c = "";
				d += " Difficulty=";
			} else {
				d += " AND Difficulty=";
			}
			d += dif[1].replaceAll(",", " OR ");
		}

		/*
		 * PRICE UIT DE LINK HALEN
		 */
		String[] pri = splittedLink[2].split("=");
		if (pri.length >= 2) {

			if (d.equals(" Difficulty=0") || d.equals(" AND Difficulty=0")) {

				// geen difficulty gekozen
				d = "";
				p += " Price=";
			} else {
				p += " AND Price=";
			}
			p += pri[1].replaceAll(",", " OR ");
		}

		/*
		 * TIME UIT DE LINK HALEN
		 */
		String[] tim = splittedLink[3].split("=");
		if (tim.length >= 2) {

			if (p.equals(" Price=0") || p.equals(" AND Price=0")) {

				// geen prijs gekozen
				p = "";
				t += " time <";
			} else {
				t += " AND time <";
			}
			t += tim[1].replaceAll(",", " OR ");
		}

		// geen time gekozen
		if (t.equals(" time <0") || t.equals(" AND time <0")) {
			t = "";
		}

		// querie maken
		String query = "";

		// zonder join
		if (c.equals("Category_Category_ID=0")) {
			query = "SELECT * FROM recipe WHERE " + c + d + p + t + ";";
		} else {
			// met join
			query = "SELECT * FROM recipe "
					+ "JOIN recipe_has_category on recipe_has_category.Recipe_Recipe_ID = recipe.recipe_ID" + " WHERE "
					+ c + d + p + t + ";";
		}
		
		System.out.println(query);


		// nu query doorgeven aan de repository
		RecipeRepository rr = new RecipeRepository();

		// resultset in JSONformaat terug geven
		return rr.Filter(query);
	}

}
