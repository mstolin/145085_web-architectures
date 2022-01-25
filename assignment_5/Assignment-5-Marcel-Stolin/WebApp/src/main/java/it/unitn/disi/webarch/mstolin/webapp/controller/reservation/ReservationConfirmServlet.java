package it.unitn.disi.webarch.mstolin.webapp.controller.reservation;

import it.unitn.disi.webarch.mstolin.dao.accommodation.AccommodationEntity;
import it.unitn.disi.webarch.mstolin.dao.accommodation.HotelEntity;
import it.unitn.disi.webarch.mstolin.dao.reservation.HotelReservationEntity;
import it.unitn.disi.webarch.mstolin.dao.reservation.ReservationEntity;
import it.unitn.disi.webarch.mstolin.webapp.helper.ControllerHelper;
import it.unitn.disi.webarch.mstolin.webapp.services.delegates.AccommodationDelegate;
import it.unitn.disi.webarch.mstolin.webapp.services.delegates.ReservationDelegate;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.sql.Date;

public class ReservationConfirmServlet extends HttpServlet {

    private AccommodationDelegate accommodationDelegate = new AccommodationDelegate();
    private ReservationDelegate reservationDelegate = new ReservationDelegate();

    private ReservationEntity generateReservationEntity(AccommodationEntity accommodationEntity, String guestName, Date startDate, Date endDate, int persons, double totalPrice, String creditCard) {
        if (accommodationEntity instanceof HotelEntity) {
            HotelEntity hotelEntity = (HotelEntity) accommodationEntity;
            return new HotelReservationEntity(guestName, hotelEntity, startDate, endDate, persons, totalPrice, creditCard);
        } else {
            return new ReservationEntity(guestName, accommodationEntity, startDate, endDate, totalPrice, creditCard);
        }
    }

    private String generateGuestName(String firstName, String lastName) {
        firstName = firstName.trim();
        lastName = lastName.trim();
        return firstName + " " + lastName;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accommodationIdParameter = request.getParameter("accommodationId");
        String startDateParameter = request.getParameter("startDate");
        String endDateParameter = request.getParameter("endDate");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String creditCard = request.getParameter("creditCard");
        String numberOfPersonsParameter = request.getParameter("numberPersons");
        String isHalfBoardRequestedParameter = request.getParameter("isHalfBoardRequested");

        if (accommodationIdParameter != null && startDateParameter != null && endDateParameter != null && firstName != null && lastName != null && numberOfPersonsParameter != null && creditCard != null) {
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

                // check validity
                if (numberOfPersons >= 1 && startDate.before(endDate) && firstName.length() > 0 && lastName.length() > 0 && creditCard.length() > 0) {
                    String guestName = this.generateGuestName(firstName, lastName);
                    AccommodationEntity accommodation = this.accommodationDelegate.getAccommodation(accommodationId);
                    double totalPrice = this.reservationDelegate.getPriceForReservation(accommodation, startDate, endDate, numberOfPersons, isHalfBoardRequested);
                    ReservationEntity reservation = this.generateReservationEntity(accommodation, guestName, startDate, endDate, numberOfPersons, totalPrice, creditCard);
                    this.reservationDelegate.addReservation(reservation);

                    this.getServletContext()
                            .getRequestDispatcher("/views/reservation/ReservationConfirmView.jsp")
                            .forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                }
            } catch (ParseException exception) {
                exception.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (NamingException exception) {
                exception.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
    }

}
