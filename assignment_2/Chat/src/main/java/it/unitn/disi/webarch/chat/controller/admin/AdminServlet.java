package it.unitn.disi.webarch.chat.controller.admin;

import it.unitn.disi.webarch.chat.helper.UserStore;
import it.unitn.disi.webarch.chat.models.user.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext()
                .getRequestDispatcher("/views/Admin.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username != null && password != null) {
            // valid request
            if (this.areUsernamePasswordValid(username, password)) {
                // valid credentials, add user
                User user = new User(username, password);
                UserStore.getInstance().add(user);

                try {
                    UserStore.getInstance().addUser(user);
                } catch (Exception exception) {
                    System.out.println("ERROR (AdminServlet) - " + exception.getMessage());
                } finally {
                    // forward
                    this.doGet(request, response);
                }
            } else {
                // invalid credentials -> reload page
                this.doGet(request, response);
            }
        } else {
            // invalid request
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private boolean areUsernamePasswordValid(String username, String password) {
        return username.length() >= 1 && password.length() >= 1;
    }
}