package it.unitn.disi.webarch.mstolin.webapp.models.accommodation;

import it.unitn.disi.webarch.mstolin.dao.accommodation.AccommodationEntity;

import java.io.Serializable;
import java.util.List;

public class AccommodationSearchResult implements Serializable {

    private List<AccommodationEntity> accommodations;

    public AccommodationSearchResult() {
    }

    public AccommodationSearchResult(List<AccommodationEntity> accommodations) {
        this.accommodations = accommodations;
    }

    public List<AccommodationEntity> getAccommodations() {
        return accommodations;
    }

    public void setAccommodations(List<AccommodationEntity> accommodations) {
        this.accommodations = accommodations;
    }

}
