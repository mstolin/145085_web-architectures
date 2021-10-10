package it.unitn.disi.webarch.chat.controller.auth;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AuthLogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (this.isAuthenticated(session)) {
            // Logout
            System.out.println("Logout user");
            session.invalidate();

            // go back to login
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            // Otherwise send error code
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    private boolean isAuthenticated(HttpSession session) {
        Object authSessionAttr = session.getAttribute("is_authenticated");
        if (authSessionAttr != null) {
            boolean isAuthenticated = (boolean)authSessionAttr;
            return isAuthenticated;
        }

        return false;
    }

}
