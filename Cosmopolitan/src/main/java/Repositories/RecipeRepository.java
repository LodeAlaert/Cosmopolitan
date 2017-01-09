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

	// Get the recipe from the DB by the selected ID
	public String GetRecipeByID(int id) {
		String query = "SELECT * FROM recipe where Recipe_ID = " + id;
		List<JSONObject> JSONResult = new ArrayList<JSONObject>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://10.129.32.103:3306/cosmo?autoReconnect=true&useSSL=false",
					"Cosmo", "Cosmo123");

			// execute the query
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			// convert the resultset to JSON format
			ResultSetToJSONConverter rstjc = new ResultSetToJSONConverter();
			JSONResult = rstjc.getFormattedResult(rs);
			System.out.println(JSONResult);

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return JSONResult.toString();
	}

	// Filter method, the query is created in the RecipeController
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
	
	// method for the search bar, multiple words can be searched
	public String GetMatchingrecepies(String searchs) {

		String[] parts = searchs.split("_");
		String search = "";
		
		String recipes = "";
		String name ="";
		
		boolean start = true;

		for (int i = 0; i < parts.length; i++) {

			if (start) {
				recipes += "'%" + parts[i] + "%'";
				name += "'%" + parts[i] + "%'";
				
				start = false;
				
			} else {
				recipes += " OR recipe like '%" + parts[i] + "%'";
				name += " OR name like '%" + parts[i] + "%'";
			}

		}
		
		String query = " SELECT * FROM recipe where recipe like " + recipes + " OR name like " + name +";";
		System.out.println(query);
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

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return JSONResult.toString();
	}
}
