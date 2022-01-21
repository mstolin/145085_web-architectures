package it.unitn.disi.webarch.mstolin.webservices.reservations;

import it.unitn.disi.webarch.mstolin.dao.accommodation.ApartmentEntity;
import it.unitn.disi.webarch.mstolin.dao.accommodation.HotelEntity;
import it.unitn.disi.webarch.mstolin.dao.reservation.ReservationEntity;

import java.util.Date;
import java.util.List;

public interface ReservationService {

    public double calculateApartmentReservationPrice(ApartmentEntity apartment, Date startDate, Date endDate);

    public double calculateHotelReservationPrice(HotelEntity hotel, Date startDate, Date endDate, int guests, boolean isHalfBoardRequested);

    public ReservationEntity getReservation(int id);

    public List<ReservationEntity> getReservations();

    public List<ReservationEntity> getReservationsForGuest(String guestName);

    public void persistReservation(ReservationEntity reservationEntity);

}
