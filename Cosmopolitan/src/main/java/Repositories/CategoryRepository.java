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

@Repository
public class CategoryRepository {

    // variables
    private DataSource dataSource;
    private static java.sql.Connection conn;

    // datasource for the DB settings
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // fetch all the categories from the database
    public String GetAllCategories() {

        String query = "SELECT * FROM category";
        // Connection conn = null;

        List<JSONObject> JSONResult = new ArrayList<JSONObject>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://10.129.32.103:3306/cosmo?autoReconnect=true&useSSL=false",
                    "Cosmo", "Cosmo123");//sslfalse anders warning 

            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            //to json
            ResultSetToJSONConverter rstjc = new ResultSetToJSONConverter();
            JSONResult = rstjc.getFormattedResult(rs);

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return JSONResult.toString();
    }
}
