package it.unitn.disi.webarch.mstolin.webapp.controller.accommodation;

import it.unitn.disi.webarch.mstolin.dao.accommodation.AccommodationEntity;
import it.unitn.disi.webarch.mstolin.webapp.helper.ControllerHelper;
import it.unitn.disi.webarch.mstolin.webapp.models.accommodation.AccommodationResultDetail;
import it.unitn.disi.webarch.mstolin.webapp.models.accommodation.AccommodationSearchResult;
import it.unitn.disi.webarch.mstolin.webapp.services.delegates.AccommodationDelegate;
import it.unitn.disi.webarch.mstolin.webapp.services.delegates.ReservationDelegate;

import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class AccommodationResultServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(AccommodationResultServlet.class.getName());
    private AccommodationDelegate accommodationDelegate = new AccommodationDelegate();
    private ReservationDelegate reservationDelegate = new ReservationDelegate();

    private List<AccommodationResultDetail> getAccommodationResults(Date startDate, Date endDate, int persons) throws NamingException, IOException {
        List<AccommodationEntity> accommodationEntities = this.accommodationDelegate.getAvailableAccommodations(startDate, endDate, persons);
        List<AccommodationResultDetail> accommodationResults = new ArrayList<>();
        for (AccommodationEntity accommodationEntity: accommodationEntities) {
            double totalPrice = 0;
            totalPrice = this.reservationDelegate.getPriceForReservation(accommodationEntity, startDate, endDate, persons, false);
            double totalPriceExtraHalfBoard = this.reservationDelegate.getPriceForReservation(accommodationEntity, startDate, endDate, persons, true);
            AccommodationResultDetail resultDetail = new AccommodationResultDetail(accommodationEntity, totalPrice, totalPriceExtraHalfBoard);
            accommodationResults.add(resultDetail);
        }
        return accommodationResults;
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
                Date startDate = ControllerHelper.parseStringToDate(startDateParameter);
                Date endDate = ControllerHelper.parseStringToDate(endDateParameter);
                int numberOfPersons = Integer.parseInt(numberOfPersonsParameter);

                // check if parameters are valid
                if (numberOfPersons >= 1 && endDate.after(startDate)) {
                    // get results and create result bean
                    List<AccommodationResultDetail> results = this.getAccommodationResults(startDate, endDate, numberOfPersons);
                    AccommodationSearchResult searchResult = new AccommodationSearchResult(results);
                    this.logger.info("Received " + results.size() + " results");
                    request.setAttribute("searchResult", searchResult);

                    this.getServletContext()
                            .getRequestDispatcher("/views/accommodation/AccommodationResultView.jsp")
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
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (NamingException exception) {
                exception.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
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
