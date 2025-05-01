package com.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/stockManagement")
public class StockManagementServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Check for valid session
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.html");
            return;
        }

        // Read theme preference from cookies
        String theme = "default";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("theme".equals(cookie.getName())) {
                    theme = cookie.getValue();
                    break;
                }
            }
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Apply theme display (can later be used for CSS)
        out.println("<html><head><title>Stock Management</title></head><body>");
        out.println("<h2>Welcome, " + session.getAttribute("username") + "</h2>");
        out.println("<p>Current Theme: <strong>" + theme + "</strong></p>");
        out.println("<h3>Stock List</h3>");
        out.println("<a href='addStock.html'>Add New Stock</a><br><br>");

        try (Connection conn = DatabaseUtil.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM stock");

            out.println("<table border='1'>");
            out.println("<tr><th>ID</th><th>Product Name</th><th>Quantity</th><th>Price</th><th>Actions</th></tr>");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("product_name");
                int qty = rs.getInt("quantity");
                double price = rs.getDouble("price");

                out.println("<tr>");
                out.println("<td>" + id + "</td>");
                out.println("<td>" + name + "</td>");
                out.println("<td>" + qty + "</td>");
                out.println("<td>" + price + "</td>");
                out.println("<td>");
                out.println("<a href='updateStock?id=" + id + "'>Update</a> | ");
                out.println("<a href='deleteStock?id=" + id + "'>Delete</a>");
                out.println("</td>");
                out.println("</tr>");
            }

            out.println("</table>");

        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<p>Error fetching stock data.</p>");
        }

        out.println("</body></html>");
    }
}
