package it.unitn.disi.webarch.memory.memory.controller.welcome;

import it.unitn.disi.webarch.memory.memory.models.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class WelcomeServlet extends HttpServlet {

    private final String REQUEST_KEY_USERNAME = "REQUEST_KEY_USERNAME";
    private final String SESSION_KEY_USER = "SESSION_KEY_USER";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext()
                .getRequestDispatcher("/views/welcome/index.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // parse request
        String username = (String)request.getAttribute(REQUEST_KEY_USERNAME);
        if (username != null && username.length() >= 1) {
            HttpSession session = request.getSession();
            User user = new User(username);
            session.setAttribute(SESSION_KEY_USER, username);
        } else {
            response.sendError(400);
        }
    }

}
