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

@Repository
public class CategoryRepository {

	private DataSource dataSource;
	private static java.sql.Connection conn;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public String GetAllCategories() {

		String query = "SELECT * FROM category";
		// Connection conn = null;

		List<String> lijstVanCategories = new ArrayList<String>();
		List<JSONObject> JSONResult = new ArrayList<JSONObject>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://10.129.32.103:3306/cosmo?autoReconnect=true&useSSL=false", "Cosmo", "Cosmo123");
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			JSONResult = getFormattedResult(rs);
			System.out.println(JSONResult);

			/*
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();

			while (rs.next()) {
				for (int i = 1; i <= columnsNumber; i++) {
					if (i > 1)
						System.out.print(",  ");
					String columnValue = rs.getString(i);
					System.out.print(columnValue + " " + rsmd.getColumnName(i));
					lijstVanCategories.add(columnValue);
				}
				System.out.println("");
			}
			*/
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return JSONResult.toString();
	}

	/*
	 * Convert ResultSet to a common JSON Object array Result is like:
	 * [{"ID":"1","NAME":"Tom","AGE":"24"}, {"ID":"2","NAME":"Bob","AGE":"26"},
	 * ...]
	 */
	public static List<JSONObject> getFormattedResult(ResultSet rs) {
		List<JSONObject> resList = new ArrayList<JSONObject>();
		try {
			// get column names
			ResultSetMetaData rsMeta = rs.getMetaData();
			int columnCnt = rsMeta.getColumnCount();
			List<String> columnNames = new ArrayList<String>();
			for (int i = 1; i <= columnCnt; i++) {
				columnNames.add(rsMeta.getColumnName(i).toUpperCase());
			}

			while (rs.next()) { // convert each object to an human readable JSON
								// object
				JSONObject obj = new JSONObject();
				for (int i = 1; i <= columnCnt; i++) {
					String key = columnNames.get(i - 1);
					String value = rs.getString(i);
					obj.put(key, value);
				}
				resList.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resList;
	}
}
