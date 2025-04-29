package jdbcexample;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import jdbcexample.Employee;
import jdbcexample.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    // Create an employee
    public static void addEmployee(String name, String position, double
    salary) {
    String sql = "INSERT INTO employees (name, position, salary) VALUES(?, ?, ?)";
    try (Connection conn = DatabaseConnection.getConnection();
    PreparedStatement stmt = conn.prepareStatement(sql)) {
    stmt.setString(1, name);
    stmt.setString(2, position);
    stmt.setDouble(3, salary);
    int rowsAffected = stmt.executeUpdate();
    System.out.println("Employee added successfully. Rows affected: "
    + rowsAffected);
    } catch (SQLException e) {
    e.printStackTrace();
    }
    }
    // Read all employees
    public static List<Employee> getAllEmployees() {
    List<Employee> employees = new ArrayList<>();
    String sql = "SELECT * FROM employees";
    try (Connection conn = DatabaseConnection.getConnection();
    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery(sql)) {
    while (rs.next()) {
    Employee employee = new Employee( rs.getInt("id"),rs.getString("name"), rs.getString("position"),rs.getDouble("salary"));
            employees.add(employee);
            }
            } catch (SQLException e) {
            e.printStackTrace();
            }
            return employees;
            }
            // Update an employee's information
            public static void updateEmployee(int id, String name, String position,
            double salary) {
            String sql = "UPDATE employees SET name = ?, position = ?, salary = ?WHERE id = ?";
            try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, position);
            stmt.setDouble(3, salary);
            stmt.setInt(4, id);
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Employee updated successfully. Rows affected:" + rowsAffected);
            } catch (SQLException e) {
            e.printStackTrace();
            }
            }
            // Delete an employee
            public static void deleteEmployee(int id) {
            String sql = "DELETE FROM employees WHERE id = ?";
            try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Employee deleted successfully. Rows affected:" + rowsAffected);
            } catch (SQLException e) {
            e.printStackTrace();
        }
        
        }

}
