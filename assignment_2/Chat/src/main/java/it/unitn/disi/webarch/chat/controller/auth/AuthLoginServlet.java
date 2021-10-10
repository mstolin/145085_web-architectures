package it.unitn.disi.webarch.chat.controller.auth;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AuthLoginServlet extends HttpServlet {

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
            if (this.authorizedUsers.containsKey(username)) {
                System.out.println("USER " + username + " EXISTS");
                String userPassword = this.authorizedUsers.get(username);

                if (userPassword.equals(password)) {
                    // user is authenticated
                    this.updateUserAuthStatus(request, true);
                    // redirect to user page
                    response.sendRedirect(request.getContextPath() + "/user");
                }
            }
        }
    }

    private void updateUserAuthStatus(HttpServletRequest request, boolean isAuthenticated) {
        HttpSession session = request.getSession();
        session.setAttribute("is_authenticated", isAuthenticated);
    }

}
