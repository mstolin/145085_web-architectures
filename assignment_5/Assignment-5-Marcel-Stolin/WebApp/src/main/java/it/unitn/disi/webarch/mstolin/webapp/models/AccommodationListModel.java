package it.unitn.disi.webarch.mstolin.webapp.models;

import it.unitn.disi.webarch.mstolin.dao.accommodation.AccommodationEntity;
import it.unitn.disi.webarch.mstolin.webapp.servicelocator.ServiceFactory;
import it.unitn.disi.webarch.mstolin.webservices.accommodations.AccommodationService;

import javax.naming.NamingException;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class AccommodationListModel implements Serializable {

    private AccommodationService accommodationService;

    public AccommodationListModel() {
        try {
            this.accommodationService = ServiceFactory.initializeService(
                    ServiceFactory.ACCOMMODATION_BEAN,
                    AccommodationService.class.getName()
            );
        } catch (NamingException e) {
            e.printStackTrace();
            this.accommodationService = null;
        }
    }

    public List<AccommodationEntity> getAllAccommodations() {
        if (this.accommodationService != null) {
            return this.accommodationService.getAll();
        } else {
            return Collections.emptyList();
        }
    }

}
