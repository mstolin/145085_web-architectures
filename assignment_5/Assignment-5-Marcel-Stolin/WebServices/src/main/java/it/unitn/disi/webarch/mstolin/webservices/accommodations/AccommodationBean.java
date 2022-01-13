package it.unitn.disi.webarch.mstolin.webservices.accommodations;

import it.unitn.disi.webarch.mstolin.dao.accommodation.AccommodationEntity;
import it.unitn.disi.webarch.mstolin.dao.accommodation.ApartmentEntity;
import it.unitn.disi.webarch.mstolin.dao.accommodation.HotelEntity;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
@Remote(AccommodationService.class)
public class AccommodationBean implements AccommodationService {
    private final String ACCOMMODATION_ENTITY_NAME = "AccommodationEntity";
    private final String APARTMENT_ENTITY_NAME = "ApartmentEntity";
    private final String HOTEL_ENTITY_NAME = "HotelEntity";

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

    @Override
    public List<AccommodationEntity> getAll() {
        String hqlQuery = "FROM " + this.ACCOMMODATION_ENTITY_NAME;
        Query query = entityManager.createQuery(hqlQuery);
        List<AccommodationEntity> result = (List<AccommodationEntity>) query.getResultList();
        return result;
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