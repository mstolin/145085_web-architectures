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
            // valid request, check if user already exists
            User user = UserStore.getInstance().getUser(username);
            if (user == null) {
                // user does not exist
                if (this.areUsernamePasswordValid(username, password)) {
                    // valid credentials, add user
                    User newUser = new User(username, password);

                    try {
                        UserStore.getInstance().addUser(newUser);
                    } catch (Exception exception) {
                        System.out.println("ERROR (AdminServlet) - " + exception.getMessage());
                    } finally {
                        // forward
                        this.doGet(request, response);
                    }
                } else {
                    // invalid credentials -> reload page
                    System.out.println("ERROR (AdminServlet) - Credentials are invalid: username: " + username + ", password: " + password);
                    this.doGet(request, response);
                }
            } else {
                // user already exist -> Reload
                System.out.println("ERROR (AdminServlet) - User " + username + " already exist");
                this.doGet(request, response);
            }
        } else {
            // invalid request
            System.out.println("ERROR (AdminServlet) - Invalid request");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private boolean areUsernamePasswordValid(String username, String password) {
        // username is not allowed to be admin
        return username.length() >= 1 && password.length() >= 1 && !username.equals("admin");
    }
}
