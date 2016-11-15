/*
 * Deze klasse zal gebruikt worden wanneer de server een request ontvang met "/categories"
 * Hier zal dan de repository worden aangesproken om een lijst op te halen met alle categorieën
 * uit de gegevensbank. De server zal dan antwoorden met die lijst van categorieën
 */

package Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import Models.Category;
import Models.CategoryDAO;

@Controller
public class CategoryController {
	
	@RequestMapping("/categories")
	@ResponseBody
	public String GetCategories(){
		return "dit zijn alle categorieën";
	}
}
