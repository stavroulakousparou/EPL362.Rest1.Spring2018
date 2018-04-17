package com.statare.statare;
/**
 * This class is responsible for connecting the application with the database and hold that
 * connection, or it keeps it as a service so other classes can use it.
 */

import android.os.StrictMode;
import android.util.Log;

import java.sql.*;

public class ConnectDB {

    // the connection string that has the username and the password of the database
    private static final String dbConnString = "jdbc:mysql://phpmyadmin.in.cs.ucy.ac.cy/stat?" + "user=stat&password=MKtYyjtfaEuFpaXv";
    public static Connection conn = null; // the connection
    private static final String TAG = "Statare"; // the logcat tag


    /**
     * This method returns a connection to MySQL server Database.
     *
     * @return The connection.
     */

    public Connection establishConnection() {

        Log.i(TAG, "Connecting database...");

        // with threads is trying to connect with the MySQL Driver
        try {

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("com.mysql.jdbc.Driver");

        } catch (Exception e) {
            Log.e(TAG, "Class forname error: " +e.getMessage());
        }

        // connect with the database through the driver manager
        try {

            if (conn == null)
                conn = DriverManager.getConnection(dbConnString);

            else if (conn.isClosed())
                conn = DriverManager.getConnection(dbConnString);

            Log.i(TAG, "Database connected!");
        } catch (SQLException e) {
            Log.e(TAG, "Cannot connect to the DB!\nGot error: \n" + e.getErrorCode());
            Log.e(TAG, "\nSQL State: \n" + e.getSQLState());
            Log.e(TAG, e.getMessage());
        }

        return conn;
    }

}