package it.unitn.disi.webarch.mstolin.webservices.reservations;

import it.unitn.disi.webarch.mstolin.dao.accommodation.ApartmentEntity;
import it.unitn.disi.webarch.mstolin.dao.accommodation.HotelEntity;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Stateless
@Remote(ReservationService.class)
public class ReservationBean implements ReservationService  {

    private long getDifferenceOfDates(Date startDate, Date endDate) {
        long diffInMillies = Math.abs(startDate.getTime() - endDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return diff;
    }

    @Override
    public double calculateApartmentReservationPrice(ApartmentEntity apartment, Date startDate, Date endDate) {
        long nights = this.getDifferenceOfDates(startDate, endDate);
        return (apartment.getPrice() * nights) + apartment.getFinalCleaningFee();
    }

    @Override
    public double calculateHotelReservationPrice(HotelEntity hotel, Date startDate, Date endDate, int guests, boolean isHalfBoardRequested) {
        long nights = this.getDifferenceOfDates(startDate, endDate);
        double dailyPrice = hotel.getPrice();
        if (isHalfBoardRequested) {
            dailyPrice = dailyPrice + hotel.getExtraHalfBoard();
        }
        return (dailyPrice * guests) * nights;
    }

}
