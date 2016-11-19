package Repositories;

// imports
// source: https://www.mkyong.com/spring/maven-spring-jdbc-example/ 	
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.*;

// project ben
import java.*;


@Repository
public class CategoryRepository {

	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}	

	public String GetAllCategories() {

		
		String query = "SELECT * FROM categories";
		Connection conn = null;
		
		/*
		try {
			conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			System.out.println("------------------------------------------------------------------");
			System.out.println(rs.toString());
			

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		*/
		return "Hier zal een lijst van alle categoriën uit komen";
	}
}
