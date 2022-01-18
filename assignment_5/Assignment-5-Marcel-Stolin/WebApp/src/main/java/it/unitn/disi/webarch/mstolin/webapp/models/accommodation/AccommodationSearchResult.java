package it.unitn.disi.webarch.mstolin.webapp.models.accommodation;

import java.io.Serializable;
import java.util.List;

public class AccommodationSearchResult implements Serializable {

    private List<AccommodationResultDetail> results;

    public AccommodationSearchResult() {
    }

    public AccommodationSearchResult(List<AccommodationResultDetail> results) {
        this.results = results;
    }

    public List<AccommodationResultDetail> getResults() {
        return results;
    }

    public void setResults(List<AccommodationResultDetail> results) {
        this.results = results;
    }

}
