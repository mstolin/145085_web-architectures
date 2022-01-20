package it.unitn.disi.webarch.mstolin.entities.occupancy;

import it.unitn.disi.webarch.mstolin.entities.accommodation.AccommodationEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.sql.Date;

@Entity
@DiscriminatorValue("APARTMENT_OCCUPANCY")
public class ApartmentOccupancyEntity extends AccommodationOccupancyEntity {
    @Basic
    @Column(name = "IS_AVAILABLE", columnDefinition = "INTEGER")
    private boolean isAvailable;

    public ApartmentOccupancyEntity() {
    }

    public ApartmentOccupancyEntity(AccommodationEntity accommodation, Date day, boolean isAvailable) {
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
