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

@RestController
public class RecipeController {
	
	@Autowired
	RecipeRepository rr;
	
	// this catches the /recipe/{id} request
	@RequestMapping(value = "/recipe/{id}", method= RequestMethod.GET)
	public String GetRecipeByID(@PathVariable("id") int id){
		return rr.GetRecipeByID(id);
	}
}
