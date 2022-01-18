package it.unitn.disi.webarch.mstolin.webservices.reservations;

import it.unitn.disi.webarch.mstolin.dao.accommodation.ApartmentEntity;
import it.unitn.disi.webarch.mstolin.dao.accommodation.HotelEntity;

import java.util.Date;

public interface ReservationService {

    public double calculateApartmentReservationPrice(ApartmentEntity apartment, Date startDate, Date endDate);

    public double calculateHotelReservationPrice(HotelEntity hotel, Date startDate, Date endDate, int guests, boolean isHalfBoardRequested);

}
