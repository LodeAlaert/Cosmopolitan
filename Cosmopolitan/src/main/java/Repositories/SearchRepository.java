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
import java.util.Set;
import java.util.TreeSet;

import org.json.JSONObject;

@Repository
public class SearchRepository {

    // variables
    private DataSource dataSource;
    private static java.sql.Connection conn;

    // datasource for the DB settings
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    String search = "";

    // gives error
//    String[] words = sertch.split(" ");
//    Set<Integer> recipeidlist = new TreeSet<>();

    // fetch all the categories from the database
    public String GetMatchingrecepies (String Qearch) {
        search = Qearch;
        // zelfde al anders maar contains op recept (bevat alle ingredienten en veel meer);
        String query = " SELECT * FROM cosmo.recipe where recipe like '%"+ search +"%'";

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
}
// SELECT * FROM cosmo.ingredient inner join recipe_has_ingredient on
// Ingredient_ID = recipe_has_ingredient.Ingedient_Ingredient_ID inner join
// recipe on recipe_has_ingredient.Recipe_Recipe_ID = recipe.Recipe_ID ;

///SELECT * FROM cosmo.ingredient inner join recipe_has_ingredient on
//Ingredient_ID = recipe_has_ingredient.Ingedient_Ingredient_ID inner join
// recipe on recipe_has_ingredient.Recipe_Recipe_ID = 1  group by Ingredient_ID ;
