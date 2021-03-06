package it.unitn.disi.webarch.mstolin.dao.reservation;

import it.unitn.disi.webarch.mstolin.dao.accommodation.AccommodationEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;
import java.sql.Date;

@Entity
@DiscriminatorValue("HOTEL_RESERVATION")
public class HotelReservationEntity extends ReservationEntity implements Serializable {
    @Basic
    @Column(name = "GUESTS")
    int guests;

    public HotelReservationEntity() {
    }

    public HotelReservationEntity(String guestName, AccommodationEntity accommodation, Date startDate, Date endDate, int guests, double totalPrice, String creditCard) {
        super(guestName, accommodation, startDate, endDate, totalPrice, creditCard);
        this.guests = guests;
    }

    public int getGuests() {
        return guests;
    }

    public void setGuests(int guests) {
        this.guests = guests;
    }
}
