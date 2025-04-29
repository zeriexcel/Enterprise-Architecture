package jdbcexample;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL =
    "jdbc:mysql://localhost:3306/employee_db"; // Database URL
    private static final String USER = "root"; // Your MySQL username
    private static final String PASSWORD = ""; // Your MySQL password
    public static Connection getConnection() throws SQLException {
    try {
    // Load the JDBC driver
    Class.forName("com.mysql.cj.jdbc.Driver");
    // Return the database connection
    return DriverManager.getConnection(URL, USER, PASSWORD);
    } catch (ClassNotFoundException | SQLException e) {
    System.out.println("Connection failed: " + e.getMessage());
    throw new SQLException("Failed to establish connection.");
    }
    }
}
