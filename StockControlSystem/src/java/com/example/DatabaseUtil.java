package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {

    public static Connection getConnection() throws SQLException {
        try {
            // Load MySQL JDBC driver (explicitly for older GlassFish/NetBeans setups)
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found. Ensure the JAR is in your project.", e);
        }

        // JDBC URL
        String url = "jdbc:mysql://localhost:3306/stock_control?useSSL=false&serverTimezone=UTC";
        String username = "root";  // change if your MySQL username is different
        String password = "";  // change if your MySQL password is different

        // Attempt connection
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.err.println("Connection Failed! Check output console");
            e.printStackTrace();
            throw e; // rethrow to handle in servlet
        }
    }
}
