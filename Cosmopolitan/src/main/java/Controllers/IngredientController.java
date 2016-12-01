/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import Repositories.IngredientsRepository;
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

/**
 *
 * @author Olivier
 */
@RestController
public class IngredientController {
    
    @Autowired
	IngredientsRepository ir;
	public String query = "";
        
    // this catches the /recipe/{id} request
	@RequestMapping(value = "/ingredients/{id}", method = RequestMethod.GET)
	public String GetIngredientsByRecipeID(@PathVariable("id") int id) {
		
        System.out.println("test");

        
		return ir.GetIngredientsByRecipeID(id);
	}

	

	

    
    
    
    
}
