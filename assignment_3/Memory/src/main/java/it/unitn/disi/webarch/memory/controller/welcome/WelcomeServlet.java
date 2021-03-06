package it.unitn.disi.webarch.memory.controller.welcome;

import it.unitn.disi.webarch.memory.models.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.logging.Logger;

public class WelcomeServlet extends HttpServlet {

    private Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    private final String REQUEST_KEY_USERNAME = "username";
    private final String SESSION_KEY_USER = "SESSION_KEY_USER";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext()
                .getRequestDispatcher("/views/welcome/index.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.logger.info("Request has been received");
        // parse request
        String username = request.getParameter(REQUEST_KEY_USERNAME);

        if (username != null && username.length() >= 1) {
            this.logger.info("Create new user for: " + username);
            HttpSession session = request.getSession();
            User user = new User(username);
            session.setAttribute(SESSION_KEY_USER, user);

            // redirect to memory
            this.logger.info("Forward to ranking at /ranking");
            response.sendRedirect("/ranking");
        } else {
            this.logger.warning("POST Request with username " + username + " was not successful");
            response.sendError(400);
        }
    }

}
