package it.unitn.disi.webarch.mstolin.entities.reservation;

import it.unitn.disi.webarch.mstolin.entities.accommodation.AccommodationEntity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@DiscriminatorValue("HOTEL_RESERVATION")
public class HotelReservationEntity extends ReservationEntity {

    @Basic
    @Column(name = "GUESTS")
    int guests;

    public HotelReservationEntity() {
    }

    public HotelReservationEntity(String guestName, AccommodationEntity accommodation, java.sql.Date startDate, Date endDate, int guests) {
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
