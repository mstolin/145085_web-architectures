package it.unitn.disi.webarch.chat.controller.auth;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AuthLoginServlet extends HttpServlet {

    private final String SESSION_KEY_USERNAME = "username";
    private final String SESSION_KEY_IS_AUTHENTICATED = "is_authenticated";

    private Map<String, String> authorizedUsers = new HashMap<String, String>() {{
        put("user1", "pw1");
        put("user2", "pw2");
        put("admin", "root");
    }};

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println("Login attempt for user " + username);

        if (username != null && password != null) {
            if (this.authorizedUsers.containsKey(username) || username.equals("admin")) {
                String userPassword = this.authorizedUsers.get(username);

                if (userPassword.equals(password)) {
                    // user is authenticated
                    HttpSession session = request.getSession();
                    this.updateUserAuthStatus(session, username, true);
                    // redirect to user page
                    response.sendRedirect(request.getContextPath() + "/user");
                } else {
                    // Wrong password -> Back to login
                    System.out.println("ERROR (AuthLogin) - Wrong password for user " + username);
                    response.sendRedirect(request.getContextPath() + "/login");
                }
            } else {
                // The user does not exists -> Back to login
                System.out.println("ERROR (AuthLogin) - User '" + username + "' does not exist");
                response.sendRedirect(request.getContextPath() + "/login");
            }
        } else {
            // No username or password given -> Send error
            System.out.println("ERROR (AuthLogin) - Bad Request, no username or password provided");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void updateUserAuthStatus(HttpSession session, String username, boolean isAuthenticated) {
        session.setAttribute(SESSION_KEY_IS_AUTHENTICATED, isAuthenticated);
        session.setAttribute(SESSION_KEY_USERNAME, username);
    }

}
