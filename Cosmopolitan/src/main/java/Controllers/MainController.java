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

    // cons
    public MainController() {
    }
}
