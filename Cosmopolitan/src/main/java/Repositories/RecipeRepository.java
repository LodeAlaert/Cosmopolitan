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

		String query = "SELECT recipe_id, name, description FROM recipe";
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
	
	public String GetRecipeByID(int id){
		return "deze request geef een recept terug a.d.h.v. zijn ID => " +id;
	}
}
