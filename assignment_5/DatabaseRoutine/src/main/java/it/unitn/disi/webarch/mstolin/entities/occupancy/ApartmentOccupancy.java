package it.unitn.disi.webarch.mstolin.entities.occupancy;

import it.unitn.disi.webarch.mstolin.entities.accommodation.AccommodationEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("APARTMENT_OCCUPANCY")
public class ApartmentOccupancy extends AccommodationOccupancy {
    @Basic
    @Column(name = "IS_AVAILABLE")
    private boolean isAvailable;

    public ApartmentOccupancy() {
    }

    public ApartmentOccupancy(AccommodationEntity accommodation, Date day, boolean isAvailable) {
        super(accommodation, day);
        this.isAvailable = isAvailable;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
