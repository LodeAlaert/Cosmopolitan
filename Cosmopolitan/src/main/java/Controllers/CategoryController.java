package Controllers;

import java.util.List;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Models.Category;
import Repositories.CategoryRepository;

@RestController
public class CategoryController {
	
	@Autowired
	CategoryRepository cr;
	
	@RequestMapping("/categories")
	public String GetAllCategories(){
		return cr.GetAllCategories();
	}
}
