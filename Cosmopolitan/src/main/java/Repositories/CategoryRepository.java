package Repositories;

// imports
// source: https://www.mkyong.com/spring/maven-spring-jdbc-example/ 	
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.*;

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

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://10.129.32.103:3306/cosmo", "Cosmo", "Cosmo123");
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();

			while (rs.next()) {
				for (int i = 1; i <= columnsNumber; i++) {
					if (i > 1)
						System.out.print(",  ");
					String columnValue = rs.getString(i);
					System.out.print(columnValue + " " + rsmd.getColumnName(i));
				}
				System.out.println("");
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return "Hier zal een lijst van alle categoriën uit komen";
	}
}
