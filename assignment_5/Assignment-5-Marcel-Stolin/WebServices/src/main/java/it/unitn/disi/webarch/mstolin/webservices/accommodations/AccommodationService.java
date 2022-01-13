package it.unitn.disi.webarch.mstolin.webservices.accommodations;

import it.unitn.disi.webarch.mstolin.dao.accommodation.AccommodationEntity;
import it.unitn.disi.webarch.mstolin.dao.accommodation.ApartmentEntity;
import it.unitn.disi.webarch.mstolin.dao.accommodation.HotelEntity;

import java.util.List;

public interface AccommodationService {

    public List<AccommodationEntity> getAll();

    public AccommodationEntity getAccommodation(int id);

    public ApartmentEntity getApartment(int id);

    public HotelEntity getHotel(int id);

}