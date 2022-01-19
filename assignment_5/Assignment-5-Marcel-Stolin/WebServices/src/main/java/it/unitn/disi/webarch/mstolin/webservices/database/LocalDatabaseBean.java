package it.unitn.disi.webarch.mstolin.webservices.database;

import it.unitn.disi.webarch.mstolin.dao.accommodation.AccommodationEntity;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
@LocalBean
public class LocalDatabaseBean {

    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;

    public <T> T getSingleEntityForId(int id, String entityName) {
        String hqlQuery = "FROM " + entityName + " WHERE id = :id";
        Query query = entityManager
                .createQuery(hqlQuery)
                .setParameter("id", id);
        T result = (T) query.getSingleResult();
        return result;
    }

    public <T> List<T> getAllEntities(String entityName) {
        String hqlQuery = "FROM " + entityName;
        Query query = entityManager.createQuery(hqlQuery);
        return query.getResultList();
    }

    public <T> List<T> getEntitiesForQuery(String hqlQuery, Object[] parameters) {
        Query query = entityManager.createQuery(hqlQuery);
        for (int i = 0; i < parameters.length; i++) {
            query.setParameter(i, parameters[i]);
        }
        return query.getResultList();
    }

}
