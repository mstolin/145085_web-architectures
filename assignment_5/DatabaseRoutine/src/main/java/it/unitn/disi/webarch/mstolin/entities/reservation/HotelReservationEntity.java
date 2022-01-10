package it.unitn.disi.webarch.mstolin.entities.reservation;

import it.unitn.disi.webarch.mstolin.entities.accommodation.AccommodationEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@DiscriminatorValue("HOTEL_RESERVATION")
public class HotelReservationEntity extends ReservationEntity implements Serializable {

    @Basic
    @Column(name = "GUESTS")
    int guests;

    public HotelReservationEntity() {
    }

    public HotelReservationEntity(String guestName, AccommodationEntity accommodation, Date startDate, Date endDate, int guests) {
        super(guestName, accommodation, startDate, endDate);
        this.guests = guests;
    }

    public int getGuests() {
        return guests;
    }

    public void setGuests(int guests) {
        this.guests = guests;
    }

}
