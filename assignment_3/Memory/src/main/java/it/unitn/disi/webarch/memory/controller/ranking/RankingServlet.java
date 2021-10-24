package it.unitn.disi.webarch.memory.controller.ranking;

import it.unitn.disi.webarch.memory.models.Scoreboard;
import it.unitn.disi.webarch.memory.models.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.logging.Logger;

public class RankingServlet extends HttpServlet {

    private final String REQUEST_KEY_USERNAME = "username";
    private final String REQUEST_KEY_POINTS = "points";
    private final String SERVLET_KEY_SCOREBOARD = "scoreboard";

    private Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext()
                .getRequestDispatcher("/views/ranking/index.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter(REQUEST_KEY_USERNAME);
        int points = Integer.parseInt(request.getParameter(REQUEST_KEY_POINTS));

        if (this.areParametersValid(username, points)) {
            ServletContext context = request.getServletContext();
            User user = new User(username);
            this.logger.info("Updated score for user " + username);
            this.setScore(context, user, points);
        } else {
            this.logger.warning("POST Request with username " + username + " and points" + points + " was not successful");
            response.sendError(400);
        }
    }

    private boolean areParametersValid(String username, int points) {
        return username != null && username.length() >= 1 && points >= 0;
    }

    private void setScore(ServletContext context, User user, Integer points) {
        Scoreboard scoreboard = (Scoreboard) context.getAttribute(SERVLET_KEY_SCOREBOARD);
        if (scoreboard == null) {
            scoreboard = new Scoreboard();
        }

        scoreboard.addUserScore(user, points);
        context.setAttribute(SERVLET_KEY_SCOREBOARD, scoreboard);
    }

}
