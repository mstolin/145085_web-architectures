package it.unitn.disi.webarch.mstolin.webapp.services.delegates;

import it.unitn.disi.webarch.mstolin.dao.accommodation.AccommodationEntity;
import it.unitn.disi.webarch.mstolin.dao.accommodation.ApartmentEntity;
import it.unitn.disi.webarch.mstolin.dao.accommodation.HotelEntity;
import it.unitn.disi.webarch.mstolin.webapp.services.servicelocator.ServiceFactory;
import it.unitn.disi.webarch.mstolin.webservices.reservations.ReservationService;

import javax.naming.NamingException;
import java.io.IOException;
import java.util.Date;

public class ReservationDelegate extends ServiceDelegate <ReservationService> {

    public ReservationDelegate() {
        super(ServiceFactory.RESERVATION_BEAN, ReservationService.class.getName());
    }

    public double getPriceForReservation(AccommodationEntity accommodation, Date startDate, Date endDate, int persons, boolean isHalfBoardRequested) throws NamingException, IOException {
        if (accommodation instanceof ApartmentEntity) {
            ApartmentEntity apartmentEntity = (ApartmentEntity) accommodation;
            return this.getService().calculateApartmentReservationPrice(apartmentEntity, startDate, endDate);
        } else if (accommodation instanceof HotelEntity) {
            HotelEntity hotelEntity = (HotelEntity) accommodation;
            return this.getService().calculateHotelReservationPrice(hotelEntity, startDate, endDate, persons, isHalfBoardRequested);
        } else {
            throw new IOException("Accommodation is neither an ApartmentEntity nor a HotelEntity");
        }
    }

}
