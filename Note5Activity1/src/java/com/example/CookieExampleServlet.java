/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

/**
 *
 * @author User
 */
@WebServlet(name = "CookieExampleServlet", urlPatterns = {"/CookieExampleServlet"})
public class CookieExampleServlet extends HttpServlet {

   
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            // Create a cookie
            Cookie userCookie = new Cookie("username", "JohnDoe");
            // Set cookie expiry time (in seconds)
            userCookie.setMaxAge(60 * 60 * 24); // 1 day
            // Add the cookie to the response
            response.addCookie(userCookie);
            response.getWriter().println("Cookie has been set.");
        
    }
}
