package com.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve form parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Check against the database
        try (Connection conn = DatabaseUtil.getConnection()) {

            PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM users WHERE username = ? AND password = ?"
            );
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Login success: create session
                HttpSession session = request.getSession();
                session.setAttribute("username", username);

                // Set cookie to remember username
                Cookie userCookie = new Cookie("username", username);
                userCookie.setMaxAge(60 * 60 * 24 * 7); // 1 week
                response.addCookie(userCookie);

                // Set theme preference cookie
                Cookie themeCookie = new Cookie("theme", "dark"); // can be dynamic later
                themeCookie.setMaxAge(60 * 60 * 24 * 30); // 30 days
                response.addCookie(themeCookie);

                // Redirect to stock management page
                response.sendRedirect("stockManagement");

            } else {
                // Invalid login
                response.setContentType("text/html");
                response.getWriter().println("<h3>Invalid credentials. <a href='login.html'>Try again</a>.</h3>");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.setContentType("text/html");
            response.getWriter().println("<h3>Database error. Please try again later.</h3>");
        }
    }
}
