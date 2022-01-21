package it.unitn.disi.webarch.mstolin.webservices.reservations;

import it.unitn.disi.webarch.mstolin.dao.accommodation.AccommodationEntity;
import it.unitn.disi.webarch.mstolin.dao.accommodation.ApartmentEntity;
import it.unitn.disi.webarch.mstolin.dao.accommodation.HotelEntity;
import it.unitn.disi.webarch.mstolin.dao.occupancy.ApartmentOccupancyEntity;
import it.unitn.disi.webarch.mstolin.dao.occupancy.HotelOccupancyEntity;
import it.unitn.disi.webarch.mstolin.dao.reservation.HotelReservationEntity;
import it.unitn.disi.webarch.mstolin.dao.reservation.ReservationEntity;
import it.unitn.disi.webarch.mstolin.webservices.database.LocalDatabaseBean;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Stateless
@Remote(ReservationService.class)
public class ReservationBean implements ReservationService  {

    @EJB()
    private LocalDatabaseBean databaseBean;

    // DUPLICATE CODE !!!!!!
    private long getDifferenceOfDates(Date startDate, Date endDate) {
        long diffInMiles = Math.abs(startDate.getTime() - endDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMiles, TimeUnit.MILLISECONDS);
        return diff;
    }

    private void updateOccupancy(AccommodationEntity accommodationEntity, java.sql.Date dayOfYear) {
        // 1. get apartment occupancy entity
        String occupancyEntity = ApartmentOccupancyEntity.class.getSimpleName();
        String selectHqlQuery = "FROM " + occupancyEntity + " " +
                "WHERE accommodation = ?0 AND dayOfYear = ?1 AND isAvailable IS TRUE";
        Object[] parameters = new Object[]{accommodationEntity, dayOfYear};
        ApartmentOccupancyEntity apartmentOccupancyEntity = this.databaseBean.getSingleEnetityForQuery(selectHqlQuery, parameters);

        // 2. set availability
        apartmentOccupancyEntity.setAvailable(false);

        // 3. update entity
        this.databaseBean.updateEntity(apartmentOccupancyEntity);
    }

    private void updateOccupancy(HotelEntity hotelEntity, java.sql.Date dayOfYear, int reservations) {
        // 1. get hotel occupancy entity
        String occupancyEntity = HotelOccupancyEntity.class.getSimpleName();
        String selectHqlQuery = "FROM " + occupancyEntity + " " +
                "WHERE accommodation = ?0 AND dayOfYear = ?1";
        Object[] parameters = new Object[]{hotelEntity, dayOfYear};
        HotelOccupancyEntity hotelOccupancyEntity = this.databaseBean.getSingleEnetityForQuery(selectHqlQuery, parameters);

        // 2. calculate new total reservations
        int newReservations = hotelOccupancyEntity.getTotalReservations() + reservations;
        hotelOccupancyEntity.setTotalReservations(newReservations);

        // 3. update entity
        this.databaseBean.updateEntity(hotelOccupancyEntity);
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

    @Override
    public ReservationEntity getReservation(int id) {
        return this.databaseBean.getSingleEntityForId(id, ReservationEntity.class.getSimpleName());
    }

    @Override
    public List<ReservationEntity> getReservations() {
        return this.databaseBean.getAllEntities(ReservationEntity.class.getSimpleName());
    }

    @Override
    public void persistReservation(ReservationEntity reservationEntity) {
        // 1. save reservation entity
        this.databaseBean.persistEntity(reservationEntity);

        // 2. update occupancies for each day
        LocalDate startDate = reservationEntity.getStartDate().toLocalDate();
        LocalDate endDate = reservationEntity.getEndDate().toLocalDate();
        AccommodationEntity accommodationEntity = reservationEntity.getAccommodation();

        for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
            java.sql.Date sqlDate = java.sql.Date.valueOf(date);
            if (reservationEntity instanceof HotelReservationEntity) {
                int reservations = ((HotelReservationEntity) reservationEntity).getGuests();
                HotelEntity hotelEntity = (HotelEntity) accommodationEntity;
                this.updateOccupancy(hotelEntity, sqlDate, reservations);
            } else {
                this.updateOccupancy(accommodationEntity, sqlDate);
            }
        }
    }

    @Override
    public List<ReservationEntity> getReservationsForGuest(String guestName) {
        String hqlQyery = "FROM " + ReservationEntity.class.getSimpleName() + " " +
                "WHERE guestName = ?0";
        Object[] parameters = new Object[]{guestName};
        return this.databaseBean.getEntitiesForQuery(hqlQyery, parameters);
    }

}
