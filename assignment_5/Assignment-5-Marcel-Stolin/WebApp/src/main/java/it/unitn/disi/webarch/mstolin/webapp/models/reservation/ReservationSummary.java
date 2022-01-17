package it.unitn.disi.webarch.mstolin.webapp.models.reservation;

import it.unitn.disi.webarch.mstolin.dao.accommodation.AccommodationEntity;

import java.io.Serializable;
import java.util.Date;

public class ReservationSummary implements Serializable {

    private AccommodationEntity accommodation;

    private Date startDate;

    private Date endDate;

    private int numberPersons;

    public ReservationSummary() {
    }

    public ReservationSummary(AccommodationEntity accommodation, Date startDate, Date endDate, int numberPersons) {
        this.accommodation = accommodation;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberPersons = numberPersons;
    }

    public AccommodationEntity getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(AccommodationEntity accommodation) {
        this.accommodation = accommodation;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getNumberPersons() {
        return numberPersons;
    }

    public void setNumberPersons(int numberPersons) {
        this.numberPersons = numberPersons;
    }

}
