package Controllers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Repositories.RecipeRepository;

@RestController
public class MainController {

	@Autowired
	RecipeRepository rr;

	// this catches the / request
	@RequestMapping("/")
	public String GetAllRecipes() {
		return rr.FetchAllRecipes();
	}

	// deze methode zal worden opgeroepen wanneer er op de website op de knop
	// "filter" wordt geklikt
	// het "*" zal dus gelijk welke objecten die worden meegegeven in de link
	// accepteren.
	@RequestMapping("/filter*")
	public String FilterRecipes(HttpServletRequest request) {

		// hier wordt een deeltje van die link eruit gehaald, de link zal worden
		// opgesplitst in categorie, moeilijkheid, prijs en bereidingstijd
		// als deze voorkomen in de link zullen deze waarden gebruikt worden
		// om (in de repository) een query samen te stellen om de gewenste
		// recepten
		// op te halen uit de DB

		// dit is de volledige link waar alles uit gehaald kan worden:
		String queryString = request.getQueryString();

		// deze worden delen van de Query
		String c = ""; // bv "category=1 AND category=2"
		String d = "";
		String p = "";
		String t = "";

		System.out.println(queryString);

		// checken er categorieën voorkomen in de link
		String[] cat = queryString.split("category=");
		if (cat.length > 1) {

			// c aanvullen
		}

		// checken of er een difficulty voorkomt in de link
		String[] diff = queryString.split("difficulty=");
		if (diff.length > 1) {
			// d aanvullen
		}

		// checken of er een prijs voorkomt in de link
		String[] price = queryString.split("price=");
		if (price.length > 1) {
			// p aanvullen
		}

		// checken of er een tijd voorkomt in de link
		String[] time = queryString.split("time=");
		if (time.length > 1) {
			// t aanvullen
		}

		// dit zal een stuk van de Query maken bv SELECT * FROM recpies WHERE
		// category=1 AND
		// category=2 AND difficulty=1 AND price='>30" ...
		String fromLink = String.format(c + " " + d + " " + p + " " + t);
		
		// nu query doorgeven aan de repository
		
		// resultset in JSONformaat terug geven
		return "ik ben de filter";
	}
}
