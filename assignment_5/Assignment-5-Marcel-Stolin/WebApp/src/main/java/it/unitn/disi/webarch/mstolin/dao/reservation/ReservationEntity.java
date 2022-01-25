package it.unitn.disi.webarch.mstolin.dao.reservation;

import it.unitn.disi.webarch.mstolin.dao.accommodation.AccommodationEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "RESERVATION", schema = "PUBLIC", catalog = "ACCOMMODATIONS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DISC", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("BASIC_RESERVATION")
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
    private Date startDate;

    @Basic
    @Column(name = "END_DATE", nullable = false)
    private Date endDate;

    @Basic
    @Column(name = "TOTAL_PRICE", nullable = false)
    private double totalPrice;

    public ReservationEntity() {
    }

    public ReservationEntity(String guestName, AccommodationEntity accommodation, Date startDate, Date endDate, double totalPrice) {
        this.guestName = guestName;
        this.accommodation = accommodation;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
