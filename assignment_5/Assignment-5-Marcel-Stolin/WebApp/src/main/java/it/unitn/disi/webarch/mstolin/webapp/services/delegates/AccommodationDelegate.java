package it.unitn.disi.webarch.mstolin.webapp.services.delegates;

import it.unitn.disi.webarch.mstolin.dao.accommodation.AccommodationEntity;
import it.unitn.disi.webarch.mstolin.webapp.services.servicelocator.ServiceFactory;
import it.unitn.disi.webarch.mstolin.webservices.accommodations.AccommodationService;

import javax.naming.NamingException;

public class AccommodationDelegate extends ServiceDelegate <AccommodationService> {

    public AccommodationDelegate() {
        super(ServiceFactory.ACCOMMODATION_BEAN, AccommodationService.class.getName());
    }

    public AccommodationEntity getAccommodation(int id) throws NamingException {
        return this.getService().getAccommodation(id);
    }

}
