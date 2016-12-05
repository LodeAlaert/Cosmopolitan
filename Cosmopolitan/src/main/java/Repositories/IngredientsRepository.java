/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;


import org.json.JSONObject;
// imports
// source: https://www.mkyong.com/spring/maven-spring-jdbc-example/ 	
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.*;

import org.json.JSONObject;


/**
 *
 * @author Olivier
 */
@Repository
public class IngredientsRepository {
    // variables
    private DataSource dataSource;
    private static java.sql.Connection conn; 
        
    // datasource for the DB settings
    public void setDataSource(DataSource dataSource) {
	this.dataSource = dataSource;
    }
    
    
    public String GetIngredientsByRecipeID(int id) {
    	        
    	String query = "SELECT * FROM ingredient "
    			+ "JOIN recipe_has_ingredient on recipe_has_ingredient.Ingedient_Ingredient_ID = Ingredient_ID "
    			+ "WHERE recipe_has_ingredient.Recipe_Recipe_ID = " + id;
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
    
    
    
}
