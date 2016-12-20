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
		System.out.println(searchvalue);
		return rr.GetMatchingrecepies(searchvalue);
	}

	// this method gets called when the button "filter" is clicked on the website
	// "*" wil get the link created in the front-end
	@RequestMapping("/filter*")
	public String FilterRecipes(HttpServletRequest request) {
		String queryString = request.getQueryString();
		return Filter(queryString);
	}

	// fuction to spit the requested URL into a MySQL query
	public String Filter(String queryString) {

		System.out.println("querystring= " + queryString);

		// categorory part of the link
		String c = "Category_Category_ID=";

		// difficulty part of the link
		String d = "";

		// price part of the link
		String p = "";

		// time part of the link
		String t = "";

		// split at the "&"
		String[] splittedLink = queryString.split("&");
		
		// value for the placement of the AND in de query
		boolean previousValue = false;

		/*
		 * get the catogory_id's from the request
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
		 * get the difficulty_id's from the request
		 */
		String[] dif = splittedLink[1].split("=");
		if (dif.length >= 2) {

			// check if the category is 0
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
		 * get the price_id's from the request
		 */
		String[] pri = splittedLink[2].split("=");
		if (pri.length >= 2) {

			// check if the difficulty is 0
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
		 * get the time_id's from the request
		 */
		String[] tim = splittedLink[3].split("=");
		if (tim.length >= 2) {

			// check if the price is 0
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

		// no time chosen...
		if (t.equals(" Time=0") || t.equals(" AND Time=0")) {
			t = "";
		}

		// nothing chosen in the filter
		if (c.equals("") && d.equals("") && p.equals("") && t.equals("")) {

			System.out.println("voor de return");
			return rr.FetchAllRecipes();

		} else {

			// create query
			query = "SELECT DISTINCT recipe_id, name, description FROM recipe "
					+ "JOIN recipe_has_category on recipe_has_category.Recipe_Recipe_ID = recipe.recipe_ID" + " WHERE "
					+ c + d + p + t + ";";

			// resultset to JSON format
			return rr.Filter(query);
		}
	}
}