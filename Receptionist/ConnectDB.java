package Receptionist;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

	private static boolean dbDriverLoaded = false;
	private static Connection conn = null;

	static Connection getDBConnection() {

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

}