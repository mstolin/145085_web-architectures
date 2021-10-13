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

        System.out.println("AuthLoginServlet - Login attempt for user " + username);

        if (username != null && password != null) {
            UserStore userStore = new UserStore();
            if (userStore.isUserAvailable(username) || username.equals("admin")) {
                User user = userStore.getUser(username);

                if (user.isPasswordCorrect(password)) {
                    // user is authenticated
                    HttpSession session = request.getSession();
                    this.updateUserAuthStatus(session, user, true);
                    // redirect to user page
                    response.sendRedirect(request.getContextPath() + "/user");
                } else {
                    // Wrong password -> Back to login
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

    private void updateUserAuthStatus(HttpSession session, User user, boolean isAuthenticated) {
        session.setAttribute(SESSION_KEY_IS_AUTHENTICATED, isAuthenticated);
        session.setAttribute(SESSION_KEY_USER, user);
    }

}
