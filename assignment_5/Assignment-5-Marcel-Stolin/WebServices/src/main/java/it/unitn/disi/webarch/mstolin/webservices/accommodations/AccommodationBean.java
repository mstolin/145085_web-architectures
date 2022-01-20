package it.unitn.disi.webarch.mstolin.webservices.accommodations;

import it.unitn.disi.webarch.mstolin.dao.accommodation.AccommodationEntity;
import it.unitn.disi.webarch.mstolin.dao.accommodation.ApartmentEntity;
import it.unitn.disi.webarch.mstolin.dao.accommodation.HotelEntity;
import it.unitn.disi.webarch.mstolin.dao.occupancy.AccommodationOccupancyEntity;
import it.unitn.disi.webarch.mstolin.webservices.database.LocalDatabaseBean;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.sql.Date;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Stateless
@Remote(AccommodationService.class)
public class AccommodationBean implements AccommodationService {

    @EJB()
    private LocalDatabaseBean databaseBean;

    private long getDifferenceOfDates(Date startDate, Date endDate) {
        long diffInMillies = Math.abs(startDate.getTime() - endDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return diff;
    }

    @Override
    public List<AccommodationEntity> getAccommodations() {
        return this.databaseBean.getAllEntities(AccommodationEntity.class.getSimpleName());
    }

    @Override
    public List<AccommodationEntity> getAvailableAccommodations(Date startDate, Date endDate, int persons) {
        /*
        Idea:
        Select all apartments and hotels that are available or have free places between the given days.
        Count the number of occurrences of an accommodation to be equal than the number of days
        in the interval.

        Example Query (Between 01.02.22 - 10.02.22 (9 Nights)):
        SELECT a.accommodation
        FROM AccommodationOccupancyEntity a
        WHERE (((a.isAvailable IS TRUE AND a.accommodation.maxPersons >= 1) OR ((a.accommodation.places - a.totalReservations) >= 1)))
        AND a.dayOfYear BETWEEN '2022-02-01' AND '2022-02-09' # 2022-01-10 doesnt count
        GROUP BY a.accommodation.id
        HAVING COUNT(*) = 9
        */
        String entityName = AccommodationOccupancyEntity.class.getSimpleName();
        String hqlQuery = "SELECT a.accommodation " +
                "FROM " + entityName + " a " +
                "WHERE (((a.isAvailable IS TRUE AND a.accommodation.maxPersons >= ?0) OR ((a.accommodation.places - a.totalReservations) >= ?0))) " +
                "AND a.dayOfYear BETWEEN ?1 AND ?2 " +
                "GROUP BY a.accommodation.id " +
                "HAVING COUNT(*) = ?3";
        Date exclusiveEndDate = Date.valueOf(endDate.toLocalDate().minusDays(1));
        long daysDiff = this.getDifferenceOfDates(startDate, endDate);
        Object[] parameters = new Object[]{persons, startDate, exclusiveEndDate, daysDiff};
        return this.databaseBean.getEntitiesForQuery(hqlQuery, parameters);
    }

    @Override
    public AccommodationEntity getAccommodation(int id) {
        return this.databaseBean.getSingleEntityForId(id, AccommodationEntity.class.getSimpleName());
    }

    @Override
    public ApartmentEntity getApartment(int id) {
        return this.databaseBean.getSingleEntityForId(id, ApartmentEntity.class.getSimpleName());
    }

    @Override
    public HotelEntity getHotel(int id) {
        return this.databaseBean.getSingleEntityForId(id, HotelEntity.class.getSimpleName());
    }
}