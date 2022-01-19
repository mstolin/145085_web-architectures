package it.unitn.disi.webarch.mstolin.webapp.controller.reservation;

import it.unitn.disi.webarch.mstolin.dao.accommodation.AccommodationEntity;
import it.unitn.disi.webarch.mstolin.dao.accommodation.ApartmentEntity;
import it.unitn.disi.webarch.mstolin.dao.accommodation.HotelEntity;
import it.unitn.disi.webarch.mstolin.webapp.helper.AccommodationServiceHandler;
import it.unitn.disi.webarch.mstolin.webapp.helper.ControllerHelper;
import it.unitn.disi.webarch.mstolin.webapp.helper.ReservationServiceHandler;
import it.unitn.disi.webarch.mstolin.webapp.models.reservation.ReservationSummary;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Logger;

public class ReservationSummaryServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(ReservationSummaryServlet.class.getName());

    private double getPriceForReservation(AccommodationEntity accommodation, Date startDate, Date endDate, int persons, boolean isHalfBoardRequested) throws NamingException, IOException {
        if (accommodation instanceof ApartmentEntity) {
            ApartmentEntity apartmentEntity = (ApartmentEntity) accommodation;
            return ReservationServiceHandler.getInstance().getPriceForApartmentReservation(apartmentEntity, startDate, endDate);
        } else if (accommodation instanceof HotelEntity) {
            HotelEntity hotelEntity = (HotelEntity) accommodation;
            return ReservationServiceHandler.getInstance().getPriceForHotelReservation(hotelEntity, startDate, endDate, persons, isHalfBoardRequested);
        } else {
            throw new IOException("Accommodation is neither an ApartmentEntity nor a HotelEntity");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accommodationIdParameter = request.getParameter("accommodationId");
        String startDateParameter = request.getParameter("startDate");
        String endDateParameter = request.getParameter("endDate");
        String numberOfPersonsParameter = request.getParameter("numberPersons");
        String isHalfBoardRequestedParameter = request.getParameter("isHalfBoardRequested"); // can be null

        if (accommodationIdParameter != null && startDateParameter != null && endDateParameter != null && numberOfPersonsParameter != null) {
            try {
                // parse parameters
                int accommodationId = Integer.parseInt(accommodationIdParameter);
                Date startDate = ControllerHelper.parseStringToDate(startDateParameter);
                Date endDate = ControllerHelper.parseStringToDate(endDateParameter);
                int numberOfPersons = Integer.parseInt(numberOfPersonsParameter);
                boolean isHalfBoardRequested = false;
                if (isHalfBoardRequestedParameter != null) {
                    isHalfBoardRequested = Boolean.parseBoolean(isHalfBoardRequestedParameter);
                }

                AccommodationEntity accommodation = AccommodationServiceHandler
                        .getInstance()
                        .getAccommodation(accommodationId);
                double totalPrice = this.getPriceForReservation(
                        accommodation,
                        startDate,
                        endDate,
                        numberOfPersons,
                        isHalfBoardRequested
                );

                ReservationSummary reservationSummary = new ReservationSummary(
                        accommodation,
                        startDate,
                        endDate,
                        numberOfPersons,
                        isHalfBoardRequested,
                        totalPrice
                );
                request.setAttribute("reservationSummary", reservationSummary);

                this.getServletContext()
                        .getRequestDispatcher("/views/reservation/ReservationSummaryView.jsp")
                        .forward(request, response);
            } catch (NamingException exception) {
                exception.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (ParseException exception) {
                exception.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (IOException exception) {
                exception.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else {
            String message = "None or missing parameters. " +
                    "Accommodation ID: " + accommodationIdParameter +
                    " Start date: " + startDateParameter +
                    " End date: " + endDateParameter +
                    " Number of Persons: " + numberOfPersonsParameter +
                    " Is Half Board Requested: " + isHalfBoardRequestedParameter;
            this.logger.warning(message);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
