package Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Models.Category;
import Repositories.CategoryRepository;

@RestController
public class MainController {
	
	@Autowired
	CategoryRepository cr;
	
	@RequestMapping("/")
	public String Home(){
		return "Hallo ik ben de server, ga naar '/categories' om de categorieën te bekijken";
	}
}
