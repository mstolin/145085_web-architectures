package it.unitn.disi.webarch.mstolin.webapp.controller.reservation;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.logging.Logger;

public class ReservationSummaryServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(ReservationSummaryServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accommodationIDParameter = request.getParameter("accommodationId");
        String startDateParameter = request.getParameter("startDate");
        String endDateParameter = request.getParameter("endDate");
        String numberOfPersonsParameter = request.getParameter("numberPersons");

        if (accommodationIDParameter != null && startDateParameter != null && endDateParameter != null && numberOfPersonsParameter != null) {
            this.getServletContext()
                    .getRequestDispatcher("/views/reservation/ReservationSummaryView.jsp")
                    .forward(request, response);
        } else {
            String message = "None or missing parameters. " +
                    "Accommodation ID: " + accommodationIDParameter +
                    " Start date: " + startDateParameter +
                    " End date: " + endDateParameter +
                    " Number of Persons: " + numberOfPersonsParameter;
            this.logger.warning(message);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
