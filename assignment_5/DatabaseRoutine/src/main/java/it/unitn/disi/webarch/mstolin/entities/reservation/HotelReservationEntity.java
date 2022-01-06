package it.unitn.disi.webarch.mstolin.entities.reservation;

import it.unitn.disi.webarch.mstolin.entities.accommodation.AccommodationEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@DiscriminatorValue("HOTEL_RESERVATION")
public class HotelReservationEntity extends ReservationEntity implements Serializable {

    @Basic
    @Column(name = "OCCUPANCY")
    int occupancy;

    public HotelReservationEntity() {
    }

    public HotelReservationEntity(String guestName, AccommodationEntity accommodation, Date startDate, Date endDate, int occupancy) {
        super(guestName, accommodation, startDate, endDate);
        this.occupancy = occupancy;
    }

    public int getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(int occupancy) {
        this.occupancy = occupancy;
    }

}
