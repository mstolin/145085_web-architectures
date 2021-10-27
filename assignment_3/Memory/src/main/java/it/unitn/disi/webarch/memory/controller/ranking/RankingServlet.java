package it.unitn.disi.webarch.memory.controller.ranking;

import it.unitn.disi.webarch.memory.models.Scoreboard;
import it.unitn.disi.webarch.memory.models.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.logging.Logger;

public class RankingServlet extends HttpServlet {

    private final String REQUEST_KEY_POINTS = "points";
    private final String SERVLET_KEY_SCOREBOARD = "scoreboard";
    private final String SESSION_KEY_USER = "SESSION_KEY_USER";

    private Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext()
                .getRequestDispatcher("/views/ranking/index.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        User user = (User) httpSession.getAttribute(SESSION_KEY_USER);
        int points = Integer.parseInt(request.getParameter(REQUEST_KEY_POINTS));

        this.logger.info("POST request received for user " + user.getName() + " and points " + points);

        if (this.areParametersValid(user, points)) {
            ServletContext context = request.getServletContext();
            this.logger.info("Update score for user " + user.getName());
            this.setScore(context, user, points);
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            this.logger.warning("POST Request with username " + user.getName() + " and points" + points + " was not successful");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private boolean areParametersValid(User user, int points) {
        return user != null && points >= 0;
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
