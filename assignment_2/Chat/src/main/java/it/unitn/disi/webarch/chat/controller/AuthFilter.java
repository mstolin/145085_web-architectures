package it.unitn.disi.webarch.chat.controller;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

public class AuthFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        boolean isAuthenticated = false;

        if (isAuthenticated) {
            // go on as expected
            chain.doFilter(request, response);
        } else {
            // forward to login
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login");
            dispatcher.forward(request, response);
        }

    }
}
