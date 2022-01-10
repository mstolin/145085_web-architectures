package it.unitn.disi.webarch.mstolin.entities.occupancy;

import it.unitn.disi.webarch.mstolin.entities.accommodation.AccommodationEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

@Entity
@DiscriminatorValue("HOTEL_OCCUPANCY")
public class HotelOccupancy extends AccommodationOccupancy implements Serializable {
    @Basic
    @Column(name = "TOTAL_RESERVATIONS")
    private int totalReservations;

    public HotelOccupancy() {
    }

    public HotelOccupancy(AccommodationEntity accommodation, Date day, int totalReservations) {
        super(accommodation, day);
        this.totalReservations = totalReservations;
    }

    public int getTotalReservations() {
        return totalReservations;
    }

    public void setTotalReservations(int totalReservations) {
        this.totalReservations = totalReservations;
    }
}
