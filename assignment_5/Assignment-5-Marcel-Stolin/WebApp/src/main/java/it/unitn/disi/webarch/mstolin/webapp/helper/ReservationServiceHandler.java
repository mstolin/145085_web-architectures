package it.unitn.disi.webarch.mstolin.webapp.helper;

import it.unitn.disi.webarch.mstolin.dao.accommodation.ApartmentEntity;
import it.unitn.disi.webarch.mstolin.dao.accommodation.HotelEntity;
import it.unitn.disi.webarch.mstolin.webapp.servicelocator.ServiceFactory;
import it.unitn.disi.webarch.mstolin.webservices.reservations.ReservationService;

import javax.naming.NamingException;
import java.util.Date;

public class ReservationServiceHandler {

    private static ReservationServiceHandler instance = null;

    private ReservationService reservationService;

    public static synchronized ReservationServiceHandler getInstance() throws NamingException {
        if (instance == null) {
            instance = new ReservationServiceHandler();
        }
        return instance;
    }

    public ReservationServiceHandler() throws NamingException {
        this.reservationService = ServiceFactory.initializeService(
                ServiceFactory.RESERVATION_BEAN,
                ReservationService.class.getName()
        );
    }

    public double getPriceForApartmentReservation(ApartmentEntity apartmentEntity, Date startDate, Date endDate) throws NamingException {
        return this.reservationService.calculateApartmentReservationPrice(apartmentEntity, startDate, endDate);
    }

    public double getPriceForHotelReservation(HotelEntity hotelEntity, Date startDate, Date endDate, int persons, boolean isHalfBoardRequested) throws NamingException {
        return this.reservationService.calculateHotelReservationPrice(hotelEntity, startDate, endDate, persons, isHalfBoardRequested);
    }

}
