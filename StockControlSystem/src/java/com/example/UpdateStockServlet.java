package com.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/updateStock")
public class UpdateStockServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.html");
            return;
        }

        int id = Integer.parseInt(request.getParameter("id"));
        try (Connection conn = DatabaseUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM stock WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            if (rs.next()) {
                out.println("<html><head><title>Update Stock</title></head><body>");
                out.println("<h2>Update Stock Item</h2>");
                out.println("<form method='POST' action='updateStock'>");
                out.println("<input type='hidden' name='id' value='" + id + "'>");
                out.println("Product Name: <input type='text' name='product_name' value='" + rs.getString("product_name") + "' required><br>");
                out.println("Quantity: <input type='number' name='quantity' value='" + rs.getInt("quantity") + "' required><br>");
                out.println("Price: <input type='number' step='0.01' name='price' value='" + rs.getDouble("price") + "' required><br>");
                out.println("<input type='submit' value='Update'>");
                out.println("</form>");
                out.println("<br><a href='stockManagement'>Back to Stock List</a>");
                out.println("</body></html>");
            } else {
                out.println("<h3>Stock item not found.</h3>");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error retrieving stock item.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.html");
            return;
        }

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("product_name");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double price = Double.parseDouble(request.getParameter("price"));

        try (Connection conn = DatabaseUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "UPDATE stock SET product_name = ?, quantity = ?, price = ? WHERE id = ?");
            stmt.setString(1, name);
            stmt.setInt(2, quantity);
            stmt.setDouble(3, price);
            stmt.setInt(4, id);
            stmt.executeUpdate();

            response.sendRedirect("stockManagement");

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error updating stock item.");
        }
    }
}
