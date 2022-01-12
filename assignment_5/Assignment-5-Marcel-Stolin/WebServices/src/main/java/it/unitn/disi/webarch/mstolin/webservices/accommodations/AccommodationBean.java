package it.unitn.disi.webarch.mstolin.webservices.accommodations;

import it.unitn.disi.webarch.mstolin.dao.accommodation.AccommodationEntity;

import java.util.Set;

public interface AccommodationBean {

    public Set<AccommodationEntity> getAll();

}
