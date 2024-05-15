package org.example.salon;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    private static final String URL = "jdbc:postgresql://viaduct.proxy.rlwy.net:12244/railway";
    private static final String USER = "postgres";
    private static final String PASSWORD = "FQrpiREqkJrmYGtsvWCSYpcAjLXUpmLK";

    public static Connection getConnection() {
        try {
            // Registering the JDBC driver
            Class.forName("org.postgresql.Driver");

            // Establishing the connection
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Connection failure.");
            e.printStackTrace();
        }
        return null;
    }
}
