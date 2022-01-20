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

    private ReservationEntity generateReservationEntity(AccommodationEntity accommodationEntity, String guestName, Date startDate, Date endDate, int persons) {
        if (accommodationEntity instanceof HotelEntity) {
            HotelEntity hotelEntity = (HotelEntity) accommodationEntity;
            return new HotelReservationEntity(guestName, hotelEntity, startDate, endDate, persons);
        } else {
            return new ReservationEntity(guestName, accommodationEntity, startDate, endDate);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accommodationIdParameter = request.getParameter("accommodationId");
        String startDateParameter = request.getParameter("startDate");
        String endDateParameter = request.getParameter("endDate");
        String guestName = request.getParameter("guestName");
        String numberOfPersonsParameter = request.getParameter("numberPersons");

        if (accommodationIdParameter != null && startDateParameter != null && endDateParameter != null && numberOfPersonsParameter != null) {
            try {
                // parse parameters
                int accommodationId = Integer.parseInt(accommodationIdParameter);
                Date startDate = ControllerHelper.parseStringToDate(startDateParameter);
                Date endDate = ControllerHelper.parseStringToDate(endDateParameter);
                int numberOfPersons = Integer.parseInt(numberOfPersonsParameter);

                // check validity
                if (numberOfPersons >= 1 && startDate.before(endDate)) {
                    AccommodationEntity accommodation = this.accommodationDelegate.getAccommodation(accommodationId);
                    ReservationEntity reservation = this.generateReservationEntity(accommodation, guestName, startDate, endDate, numberOfPersons);
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
