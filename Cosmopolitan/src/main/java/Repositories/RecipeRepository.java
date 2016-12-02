package Repositories;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import Repositories.ResultSetToJSONConverter;

@Repository
public class RecipeRepository {

	private static java.sql.Connection conn;
	
	List<JSONObject> JSONResult = new ArrayList<JSONObject>();

	// Fetch all the info needed for the homepage of the application
	// id, name & description
	public String FetchAllRecipes() {
		
		System.out.println("allRecipes aangeroepen");

		String query = "SELECT recipe_id, name, description FROM recipe";
		List<JSONObject> JSONResult = new ArrayList<JSONObject>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://10.129.32.103:3306/cosmo?autoReconnect=true&useSSL=false",
					"Cosmo", "Cosmo123");
			
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			// to json
			ResultSetToJSONConverter rstjc = new ResultSetToJSONConverter();
			JSONResult = rstjc.getFormattedResult(rs);

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return JSONResult.toString();
	}

	// fetch a recipe by it's selected ID
	public String GetRecipeByID(int id) {
		String query = "SELECT * FROM recipe where Recipe_ID = " + id;
		// Connection conn = null;

		List<JSONObject> JSONResult = new ArrayList<JSONObject>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://10.129.32.103:3306/cosmo?autoReconnect=true&useSSL=false",
					"Cosmo", "Cosmo123");
			
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			ResultSetToJSONConverter rstjc = new ResultSetToJSONConverter();
			JSONResult = rstjc.getFormattedResult(rs);
			System.out.println(JSONResult);

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return JSONResult.toString();
	}

	public String Filter(String query) {
		
		System.out.println(query);
		List<JSONObject> JSONResult = new ArrayList<JSONObject>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://10.129.32.103:3306/cosmo?autoReconnect=true&useSSL=false",
					"Cosmo", "Cosmo123");
			
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			ResultSetToJSONConverter rstjc = new ResultSetToJSONConverter();
			JSONResult = rstjc.getFormattedResult(rs);
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return JSONResult.toString();
	}
	
	public String GetMatchingrecepies(String search) {

    	// zelfde al anders maar contains op recept (bevat alle ingredienten en veel meer);
        String query = " SELECT * FROM recipe where recipe like '%"+ search +"%' OR name like '%"+search+"%';";

        List<JSONObject> JSONResult = new ArrayList<JSONObject>();

        try {

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://10.129.32.103:3306/cosmo?autoReconnect=true&useSSL=false",
                    "Cosmo", "Cosmo123");

            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            // to json
            ResultSetToJSONConverter rstjc = new ResultSetToJSONConverter();
            JSONResult = rstjc.getFormattedResult(rs);
            System.out.println(JSONResult);

//            for (String serchword : words) {
//                while (rs.next()) {
//                    rs.getString(1);
//
//                    if (rs.getString(1).contains(serchword)) {
//                        recipeidlist.add((Integer) Integer.parseInt(rs.getString(0)));
//                    }
//                    
//                }
//            }s
//            for (int nr : recipeidlist){
//                 query = " SELECT * FROM cosmo.ingredient inner join recipe_has_ingredient on Ingredient_ID = recipe_has_ingredient.Ingedient_Ingredient_ID ";
//                 System.out.println();
//            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return JSONResult.toString();
    }
	
	// SELECT * FROM cosmo.ingredient inner join recipe_has_ingredient on
	// Ingredient_ID = recipe_has_ingredient.Ingedient_Ingredient_ID inner join
	// recipe on recipe_has_ingredient.Recipe_Recipe_ID = recipe.Recipe_ID ;

	///SELECT * FROM cosmo.ingredient inner join recipe_has_ingredient on
	//Ingredient_ID = recipe_has_ingredient.Ingedient_Ingredient_ID inner join
	// recipe on recipe_has_ingredient.Recipe_Recipe_ID = 1  group by Ingredient_ID ;

   
}
