package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectDB {

	private static boolean dbDriverLoaded = false;
	private static Connection conn = null;

	Connection getDBConnection() {
		
		String dbConnString = "jdbc:mysql://phpmyadmin.in.cs.ucy.ac.cy/statare?" + "user=statare&password=pv7VEVZcmfRLZArP";

		if (!dbDriverLoaded)
			try {

				Class.forName("com.mysql.jdbc.Driver").newInstance();
				dbDriverLoaded = true;

			} catch (Exception ex) {
				System.out.println("Cannot load DB driver!");
				return null;
			}

		try {
			if (conn == null)
				conn = DriverManager.getConnection(dbConnString);

			else if (conn.isClosed())
				conn = DriverManager.getConnection(dbConnString);

		} catch (SQLException e) {
			System.out.print("Cannot connect to the DB!\nGot error: ");
			System.out.print(e.getErrorCode());
			System.out.print("\nSQL State: ");
			System.out.println(e.getSQLState());
			System.out.println(e.getMessage());
		}
		return conn;
	}
	

	public static void main(String args[]) throws Exception {

		String fname = "";
		
		Connection connect;

		ConnectDB conObj = new ConnectDB();
		connect = conObj.getDBConnection();
		if (connect == null) {
			return;
		}

		String sql = "Select * from Employee Where Username = 'eskoul02'";
		PreparedStatement statement = null;

		statement = conn.prepareStatement(sql);
		ResultSet result = statement.executeQuery();
		if (result.next()) {
			fname = result.getString("User");
		}
		try {
			if (!conn.isClosed()) {
				System.out.println("Disconnecting from database...");
				conn.close();
				System.out.println("Done\n\nBye !");
			}
		} catch (Exception e) {

		}
		System.out.println(fname);
	}

}
