package it.unitn.disi.webarch.chat.controller.admin;

import it.unitn.disi.webarch.chat.models.user.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminFilter implements Filter {

    private final String SESSION_KEY_USER = "activeUser";
    private final String SESSION_KEY_IS_AUTHENTICATED = "is_authenticated";
    private final String ADMIN_USERNAME = "admin";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpSession session = httpRequest.getSession();
        // get credentials
        User user = (User)session.getAttribute(this.SESSION_KEY_USER);
        boolean isAuthenticated = (boolean)session.getAttribute(this.SESSION_KEY_IS_AUTHENTICATED);

        if (this.isAdminAuthenticated(user, isAuthenticated)) {
            // redirect to servlet
            chain.doFilter(request, response);
        } else {
            // send error
            System.out.println("ERROR (AdminFilter) - Request is invalid from user " + user.getName());
            HttpServletResponse httpResponse = (HttpServletResponse)response;
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    private boolean isAdminAuthenticated(User user, boolean isAuthenticated) {
        return isAuthenticated && user.getName().equals(this.ADMIN_USERNAME);
    }
}
