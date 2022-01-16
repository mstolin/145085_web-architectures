package it.unitn.disi.webarch.mstolin.webservices.accommodations;

import it.unitn.disi.webarch.mstolin.dao.accommodation.AccommodationEntity;
import it.unitn.disi.webarch.mstolin.dao.accommodation.ApartmentEntity;
import it.unitn.disi.webarch.mstolin.dao.accommodation.HotelEntity;
import it.unitn.disi.webarch.mstolin.dao.occupancy.AccommodationOccupancy;
import it.unitn.disi.webarch.mstolin.dao.occupancy.ApartmentOccupancy;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Stateless
@Remote(AccommodationService.class)
public class AccommodationBean implements AccommodationService {
    private final String ACCOMMODATION_ENTITY_NAME = "AccommodationEntity";
    private final String APARTMENT_ENTITY_NAME = "ApartmentEntity";
    private final String HOTEL_ENTITY_NAME = "HotelEntity";
    private final String ACCOMMODATION_OCCUPANCY_ENTITY_NAME = "AccommodationOccupancy";

    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;

    private <T extends AccommodationEntity> T getSingleEntity(int id, String entityName) {
        String hqlQuery = "FROM " + entityName + " WHERE id = :id";
        Query query = entityManager
                .createQuery(hqlQuery)
                .setParameter("id", id);
        T result = (T) query.getSingleResult();
        return result;
    }

    private long getDifferenceOfDates(Date startDate, Date endDate) {
        long diffInMillies = Math.abs(startDate.getTime() - endDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return diff;
    }

    @Override
    public List<AccommodationEntity> getAll() {
        String hqlQuery = "FROM " + this.ACCOMMODATION_ENTITY_NAME;
        Query query = entityManager.createQuery(hqlQuery);
        List<AccommodationEntity> result = (List<AccommodationEntity>) query.getResultList();
        return result;
    }

    @Override
    public List<AccommodationEntity> getApartments(Date startDate, Date endDate, int persons) {
        /*
        Idea:
        Select all apartments and hotels that are available or have free places between the given days.
        Count the number of occurrences of an accommodation to be equal than the number of days
        in the interval.

        Example Query:
        SELECT a.accommodation
        FROM AccommodationOccupancy a
        WHERE (((a.isAvailable IS TRUE AND a.accommodation.maxPersons >= 1) OR ((a.accommodation.places - a.totalReservations) >= 1)))
        AND a.dayOfYear BETWEEN '2022-02-01' AND '2022-02-05'
        GROUP BY a.accommodation.id
        HAVING COUNT(*) = 5
        */
        // Build query and get all occupancies
        String hqlQuery = "SELECT a.accommodation " +
                "FROM " + this.ACCOMMODATION_OCCUPANCY_ENTITY_NAME + " a " +
                "WHERE (((a.isAvailable IS TRUE AND a.accommodation.maxPersons >= :persons) OR ((a.accommodation.places - a.totalReservations) >= :persons))) " +
                "AND a.dayOfYear BETWEEN :startDate AND :endDate " +
                "GROUP BY a.accommodation.id " +
                "HAVING COUNT(*) = :daysDiff";

        long daysDiff = this.getDifferenceOfDates(startDate, endDate);
        Query query = entityManager.createQuery(hqlQuery)
                .setParameter("persons", persons)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .setParameter("daysDiff", daysDiff);
        List<AccommodationEntity> accommodationEntities = query.getResultList();
        return accommodationEntities;
    }

    @Override
    public AccommodationEntity getAccommodation(int id) {
        return this.getSingleEntity(id, this.ACCOMMODATION_ENTITY_NAME);
    }

    @Override
    public ApartmentEntity getApartment(int id) {
        return this.getSingleEntity(id, this.APARTMENT_ENTITY_NAME);
    }

    @Override
    public HotelEntity getHotel(int id) {
        return this.getSingleEntity(id, this.HOTEL_ENTITY_NAME);
    }

}