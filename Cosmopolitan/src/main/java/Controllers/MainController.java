package Controllers;

import java.util.List;
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
	public String GetAllRecipes(){
		return rr.FetchAllRecipes();
	}
	
	// this catches the / request 
	@RequestMapping("/filter*")
	public String FilterRecipes(){
		return "this is the link for the filter, this should call a method in the RecipeController";
	}
}
