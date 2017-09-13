package controll;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcSQLiteConnection {

	public JdbcSQLiteConnection() {
		try {
			Class.forName("org.sqlite.JDBC");
			String dbURL = "jdbc:sqlite:store.db";
			Connection conn = DriverManager.getConnection(dbURL);
			if (conn != null) {
				System.out.println("Connected to the database....");
				// display database information
				DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
				System.out.println("Driver name: " + dm.getDriverName());
				System.out.println("Product name: " + dm.getDatabaseProductName());
				// execute SQL statements
				System.out.println("----- Data in Calendar table -----");
				String query = "Select * from Calendar";
				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery(query);
				while (resultSet.next()) {
//					System.out.println(resultSet.getString(1));
//					System.out.println(resultSet.getString(2));
				}
				// close connection
				conn.close();
			}

		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}


}
