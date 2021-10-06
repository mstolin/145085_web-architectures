package it.unitn.disi.webarch.chat.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<form action=\"" + request.getContextPath() + "/login\" method=\"post\">");
        out.println("<label for=\"username\">Username:</label>");
        out.println("<input type=\"text\" id=\"username\" name=\"username\" />");
        out.println("<label for=\"password\">Password:</label>");
        out.println("<input type=\"password\" id=\"password\" name=\"password\" />");
        out.println("<input type=\"submit\" value=\"Login\" />");
        out.println("</form>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("USERNAME: " + request.getParameter("username"));
        System.out.println("PASSWORD: " + request.getParameter("password"));
    }
}
