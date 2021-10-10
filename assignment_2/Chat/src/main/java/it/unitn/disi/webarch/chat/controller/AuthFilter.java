package it.unitn.disi.webarch.chat.controller;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.StringTokenizer;

public class AuthFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        String path = ((HttpServletRequest) request).getRequestURI();
        StringTokenizer pathTokenizer = new StringTokenizer(path, "/");
        boolean hasRequestedAuth = pathTokenizer.hasMoreTokens() &&
                pathTokenizer.nextToken().equals("auth");

        // Forward to login except for the auth path
        if (!hasRequestedAuth) {
            boolean isAuthenticated = this.isAuthenticated((HttpServletRequest)request);

            if (isAuthenticated) {
                // go on as expected
                chain.doFilter(request, response);
            } else {
                // user has to login first -> forward to login
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login");
                dispatcher.forward(request, response);
            }
        } else {
            // user want to login or to logout
            String authPath = pathTokenizer.nextToken();
            request.getRequestDispatcher("/auth/" + authPath).forward(request, response);
        }
    }

    private boolean isAuthenticated(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object authSessionAttr = session.getAttribute("is_authenticated");
        if (authSessionAttr != null) {
            boolean isAuthenticated = (boolean)authSessionAttr;
            return isAuthenticated;
        }

        return false;
    }
}
