package it.unitn.disi.webarch.mstolin.webapp.helper;

import it.unitn.disi.webarch.mstolin.dao.accommodation.AccommodationEntity;
import it.unitn.disi.webarch.mstolin.webapp.servicelocator.ServiceFactory;
import it.unitn.disi.webarch.mstolin.webservices.accommodations.AccommodationService;

import javax.naming.NamingException;

public class AccommodationServiceHandler {

    private static AccommodationServiceHandler instance = null;

    private AccommodationService accommodationService;

    public static synchronized AccommodationServiceHandler getInstance() throws NamingException {
        if (instance == null) {
            instance = new AccommodationServiceHandler();
        }
        return instance;
    }

    public AccommodationServiceHandler() throws NamingException {
        this.accommodationService = ServiceFactory.initializeService(
                ServiceFactory.ACCOMMODATION_BEAN,
                AccommodationService.class.getName()
        );
    }

    public AccommodationEntity getAccommodation(int id) throws NamingException {
        AccommodationService accommodationService = ServiceFactory.initializeService(
                ServiceFactory.ACCOMMODATION_BEAN,
                AccommodationService.class.getName()
        );
        return accommodationService.getAccommodation(id);
    }

}
