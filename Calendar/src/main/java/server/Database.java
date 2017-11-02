package server;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.CalendarInterface;
import common.DatedayInterface;

public class Database {
	public ArrayList<DatedayInterface> loadDatabase() {
		ArrayList<DatedayInterface> list2=new ArrayList<>();
		try {
			Class.forName("org.sqlite.JDBC");
			String dbURL = "jdbc:sqlite:store.db";
			Connection conn = DriverManager.getConnection(dbURL);
			if (conn != null) {
				DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
				String query = "Select * from Calendar";
				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery(query);
				
				while (resultSet.next()) {
					Dateday date=new Dateday(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
					date.setText(resultSet.getString(4));
					list2.add(date);
				}

				conn.close();
			}

		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return list2;
	}
	public void addDatabase(String day,String month,String year,String text){
		try {
			Class.forName("org.sqlite.JDBC");
			String dbURL = "jdbc:sqlite:store.db";
			Connection conn = DriverManager.getConnection(dbURL);
			if(day.length()<2){
				day="0"+day;
			}
			if (conn != null) {

				DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();

				String query = "insert into Calendar\nvalues ('" + day + "','" + month
						+ "','" + year +"','"+ text  + "')";

				Statement statement = conn.createStatement();
				statement.execute(query);

				conn.close();
			}

		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	public void updateDatabase(String day,String month,String year,String text){
		try {
			Class.forName("org.sqlite.JDBC");
			String dbURL = "jdbc:sqlite:store.db";
			Connection conn = DriverManager.getConnection(dbURL);
			if(day.length()<2){
				day="0"+day;
			}
			if (conn != null) {

				DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();

				String query = "UPDATE Calendar\nSET text='" + text+"'\nWHERE day='"+day+"' AND month='"+month+"' AND year='"+year+"'" ;

				Statement statement = conn.createStatement();
				statement.execute(query);

				conn.close();
			}

		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void delteDatabase(String day,String month,String year){
		try {
			Class.forName("org.sqlite.JDBC");
			String dbURL = "jdbc:sqlite:store.db";
			Connection conn = DriverManager.getConnection(dbURL);
			if(day.length()<2){
				day="0"+day;
			}
			if (conn != null) {

				DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
			
				String query = "DELETE FROM Calendar\nWHERE date=''";
				 query = "DELETE FROM Calendar\nWHERE day='"+day+"' AND month='"+month+"' AND year='"+year+"'" ;
				
				Statement statement = conn.createStatement();
				statement.execute(query);
			 
				conn.close();
			}

		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

}
