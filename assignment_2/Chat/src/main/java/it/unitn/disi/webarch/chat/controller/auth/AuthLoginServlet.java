package it.unitn.disi.webarch.chat.controller.auth;

import it.unitn.disi.webarch.chat.helper.UserStore;
import it.unitn.disi.webarch.chat.models.user.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class AuthLoginServlet extends HttpServlet {

    private final String SESSION_KEY_USER = "activeUser";
    private final String SESSION_KEY_IS_AUTHENTICATED = "is_authenticated";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println("INFO (AuthLoginServlet) - Login attempt for user " + username);

        if (username != null && password != null) {
            User user = UserStore.getInstance().getUser(username);
            if (user != null) {
                if (user.isPasswordCorrect(password)) {
                    this.logInUser(user, request, response);
                } else {
                    // Wrong password -> Back to login
                    System.out.println("ERROR (AuthLogin) - Wrong password for user " + username);
                    response.sendRedirect(request.getContextPath() + "/login");
                }
            } else if (username.equals("admin")) {
                String adminPassword = this.getInitParameter("AdminPassword");
                User adminUser = new User(username, adminPassword);
                if (password.equals(adminPassword)) {
                    this.logInUser(adminUser, request, response);
                } else {
                    System.out.println("ERROR (AuthLogin) - Wrong password for user " + username);
                    response.sendRedirect(request.getContextPath() + "/login");
                }
            } else {
                // The user does not exist -> Back to login
                System.out.println("ERROR (AuthLogin) - User '" + username + "' does not exist");
                response.sendRedirect(request.getContextPath() + "/login");
            }
        } else {
            // No username or password given -> Send error
            System.out.println("ERROR (AuthLogin) - Bad Request, no username or password provided");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void logInUser(User user, HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        this.updateUserAuthStatus(session, user, true);
        // redirect to user page
        response.sendRedirect(request.getContextPath() + "/user");
    }

    private void updateUserAuthStatus(HttpSession session, User user, boolean isAuthenticated) {
        session.setAttribute(SESSION_KEY_IS_AUTHENTICATED, isAuthenticated);
        session.setAttribute(SESSION_KEY_USER, user);
    }

}
