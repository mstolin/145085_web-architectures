package it.unitn.disi.webarch.mstolin.entities.occupancy;

import it.unitn.disi.webarch.mstolin.entities.accommodation.AccommodationEntity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "OCCUPANCY", schema = "PUBLIC", catalog = "ACCOMMODATIONS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DISC", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("BASIC_OCCUPANCY")
public abstract class AccommodationOccupancyEntity {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "ID", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "ACCOMMODATION_ID")
    private AccommodationEntity accommodation;

    @Column(name = "DAY_OF_YEAR", nullable = false)
    private Date dayOfYear;

    public AccommodationOccupancyEntity() {
    }

    public AccommodationOccupancyEntity(AccommodationEntity accommodation, Date dayOfYear) {
        this.accommodation = accommodation;
        this.dayOfYear = dayOfYear;
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

    public Date getDayOfYear() {
        return dayOfYear;
    }

    public void setDayOfYear(Date dayOfYear) {
        this.dayOfYear = dayOfYear;
    }
}
