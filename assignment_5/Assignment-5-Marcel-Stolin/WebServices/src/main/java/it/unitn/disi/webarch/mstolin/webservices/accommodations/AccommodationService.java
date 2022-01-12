package it.unitn.disi.webarch.mstolin.webservices.accommodations;

import it.unitn.disi.webarch.mstolin.dao.accommodation.AccommodationEntity;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.Set;

@Stateless
@Remote(AccommodationBean.class)
public class AccommodationService implements AccommodationBean {

    public Set<AccommodationEntity> getAll() {
        return null;
    }

}
