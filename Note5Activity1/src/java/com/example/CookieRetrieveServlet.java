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


@WebServlet(name = "CookieRetrieveServlet", urlPatterns = {"/CookieRetrieveServlet"})
public class CookieRetrieveServlet extends HttpServlet {

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
            if (cookies != null) {
            for (Cookie cookie : cookies) {
            if ("username".equals(cookie.getName())) {
            response.getWriter().println("Welcome back, " +
            cookie.getValue());
            }
            }
            } else {
            response.getWriter().println("No cookies found.");
            }
        }
    }

 