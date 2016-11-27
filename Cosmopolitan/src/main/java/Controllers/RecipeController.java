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
	@RequestMapping(value = "/recipe/{id}", method= RequestMethod.GET)
	public String GetRecipeByID(@PathVariable("id") int id){
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
		String c = ""; 
		String d = ""; 
		String p = "";
		String t = "";

		System.out.println(queryString);

		// checken er categorieën voorkomen in de link
		String[] cat = queryString.split("category=");
                if (cat.length > 0 && cat.length < 2) {
               
			// c aanvullen
			c += "category = " + cat[1];
		}
		else
		{
                        c = "category = ";
			// meerdere categoriën gekozen
			for(int i = 0; i <= cat.length ; i++){
                               c += cat[i]; 
                               
                               
			}
		}

		// checken of er een difficulty voorkomt in de link
		String[] diff = queryString.split("difficulty=");
		if (diff.length > 1) {
			// d aanvullen
			// bv "AND difficulty=1 "
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
		String query = String.format(c + " " + d + " " + p + " " + t);
		
		// nu query doorgeven aan de repository
		RecipeRepository rr = new RecipeRepository();
		//rr.filter(query);
		
		// resultset in JSONformaat terug geven
		return "dit zal de query zijn die nog moet worden doorgegeven aan de repository: \n"
				+ "SELECT * FROM recipes WHERE " + query;
	}
        
        
}
