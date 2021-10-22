package it.unitn.disi.webarch.memory.filters;

import it.unitn.disi.webarch.memory.models.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.logging.Logger;

public class WelcomeFilter implements Filter {

    private final String SESSION_KEY_USER = "SESSION_KEY_USER";

    private Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;

        this.logger.info("Request has been made for " + httpRequest.getRequestURI());

        String url = httpRequest.getRequestURI();
        String path = this.getFirstUrlToken(url);

        HttpSession session = httpRequest.getSession();
        User user = (User)session.getAttribute(SESSION_KEY_USER);

        if (user == null && !path.equals("welcome")) {
            // No user set
            String forwardPath = httpRequest.getContextPath() + "/welcome";
            logger.info("No user set in session");
            logger.info("Forward to " + forwardPath);
            HttpServletResponse httpResponse = (HttpServletResponse)response;
            httpResponse.sendRedirect(forwardPath);
        } else {
            chain.doFilter(request, response);
        }
    }

    private String getFirstUrlToken(String url) {
        StringTokenizer urlTokenizer = new StringTokenizer(url, "/");
        if (urlTokenizer.hasMoreTokens()) {
            return urlTokenizer.nextToken();
        }
        return "";
    }

}
