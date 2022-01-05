package it.unitn.disi.webarch.mstolin.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "RESERVATION", schema = "PUBLIC", catalog = "ACCOMMODATIONS")
public class ReservationEntity implements Serializable {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "ID", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "ACCOMMODATION_ID")
    private AccommodationEntity accommodation;

    @Basic
    @Column(name = "GUEST_NAME", nullable = false)
    private String guestName;

    @Column(name = "START_DATE", nullable = false)
    private Timestamp startDate;

    @Basic
    @Column(name = "END_DATE", nullable = false)
    private Timestamp endDate;

    public ReservationEntity() {
    }

    public ReservationEntity(String guestName, AccommodationEntity accommodation, Timestamp startDate, Timestamp endDate) {
        this.guestName = guestName;
        this.accommodation = accommodation;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AccommodationEntity getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(AccommodationEntity accommodation) {
        this.accommodation = accommodation;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

}
