package it.unitn.disi.webarch.mstolin.webapp.models.reservation;

import it.unitn.disi.webarch.mstolin.dao.reservation.ReservationEntity;

import java.io.Serializable;
import java.util.List;

public class ReservationListModel implements Serializable {

    private List<ReservationEntity> reservationEntities;

    private String guestName;

    public ReservationListModel() {
    }

    public ReservationListModel(List<ReservationEntity> reservationEntities, String guestName) {
        this.reservationEntities = reservationEntities;
        this.guestName = guestName;
    }

    public List<ReservationEntity> getReservationEntities() {
        return reservationEntities;
    }

    public void setReservationEntities(List<ReservationEntity> reservationEntities) {
        this.reservationEntities = reservationEntities;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

}
