package it.unitn.disi.webarch.mstolin.webapp.controller.accommodation;

import it.unitn.disi.webarch.mstolin.dao.accommodation.AccommodationEntity;
import it.unitn.disi.webarch.mstolin.webapp.models.accommodation.AccommodationSearchResult;
import it.unitn.disi.webarch.mstolin.webapp.servicelocator.ServiceFactory;
import it.unitn.disi.webarch.mstolin.webservices.accommodations.AccommodationService;

import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class AccommodationListServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(AccommodationListServlet.class.getName());

    private Date parseStringToDate(String dateString) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.parse(dateString);
    }

    private List<AccommodationEntity> getAvailableAccommodations(Date startDate, Date endDate, int persons) {
        try {
            AccommodationService accommodationService = ServiceFactory.initializeService(
                    ServiceFactory.ACCOMMODATION_BEAN,
                    AccommodationService.class.getName()
            );
            return accommodationService.getApartments(startDate, endDate, persons);
        } catch (NamingException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String startDateParameter = request.getParameter("startDate");
        String endDateParameter = request.getParameter("endDate");
        String numberOfPersonsParameter = request.getParameter("numberPersons");

        // check if parameters are given
        if (startDateParameter != null && endDateParameter != null && numberOfPersonsParameter != null) {
            try {
                // parse parameters to objects
                Date startDate = this.parseStringToDate(startDateParameter);
                Date endDate = this.parseStringToDate(endDateParameter);
                int numberOfPersons = Integer.parseInt(numberOfPersonsParameter);

                // check if parameters are valid
                if (numberOfPersons >= 1 && endDate.after(startDate)) {
                    // get results and create result bean
                    List<AccommodationEntity> results = this.getAvailableAccommodations(startDate, endDate, numberOfPersons);
                    AccommodationSearchResult searchResult = new AccommodationSearchResult(results);
                    request.setAttribute("searchResult", searchResult);

                    this.getServletContext()
                            .getRequestDispatcher("/views/accommodation/AccommodationListView.jsp")
                            .forward(request, response);
                } else {
                    String message = "Parameters are invalid. " +
                            "Start date: " + startDate +
                            " End date: " + endDate +
                            " Number of Persons: " + numberOfPersons;
                    this.logger.warning(message);
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                }
            } catch (ParseException exception) {
                exception.printStackTrace();
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            String message = "None or missing parameters. " +
                    "Start date: " + startDateParameter +
                    " End date: " + endDateParameter +
                    " Number of Persons: " + numberOfPersonsParameter;
            this.logger.warning(message);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
