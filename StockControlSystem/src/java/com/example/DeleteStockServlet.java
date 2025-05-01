package com.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/deleteStock")
public class DeleteStockServlet extends HttpServlet {

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
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM stock WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();

            response.sendRedirect("stockManagement");

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error deleting stock item.");
        }
    }
}
