package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/displayProducts")
public class DisplayProductsServlet extends HttpServlet {

    private Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Ensure JDBC driver is loaded
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:mysql://localhost:3306/stock_management?useSSL=false&serverTimezone=UTC";
        String username = "root"; 
        String password = ""; 
        return DriverManager.getConnection(url, username, password);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try (Connection conn = getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM stock");

            out.println("<html><body>");
            out.println("<h1>Stock List</h1>");
            out.println("<table border='1'><tr><th>ID</th><th>Product</th><th>Quantity</th></tr>");

            while (rs.next()) {
                out.println("<tr><td>" + rs.getInt("id") + "</td><td>" 
                                + rs.getString("product_name") + "</td><td>" 
                                + rs.getInt("quantity") + "</td></tr>");
            }

            out.println("</table>");
            out.println("<br><a href='index.html'>Back to Form</a>");
            out.println("</body></html>");

        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<h2>Database Error: " + e.getMessage() + "</h2>");
        }
    }
}
