package it.unitn.disi.webarch.mstolin.dao.accommodation;

import it.unitn.disi.webarch.mstolin.dao.occupancy.AccommodationOccupancyEntity;
import it.unitn.disi.webarch.mstolin.dao.reservation.ReservationEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "ACCOMMODATION", schema = "PUBLIC", catalog = "ACCOMMODATIONS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DISC", discriminatorType = DiscriminatorType.STRING)
public abstract class AccommodationEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false, updatable = false)
    protected int id;

    @Basic
    @Column(name = "NAME", nullable = false)
    protected String name;

    @Basic
    @Column(name = "PRICE", nullable = false)
    protected Double price;

    @OneToMany(cascade = { CascadeType.ALL })
    @JoinColumn(name = "ACCOMMODATION_ID")
    private Set<ReservationEntity> reservations;

    @OneToMany(cascade = { CascadeType.ALL })
    @JoinColumn(name = "ACCOMMODATION_ID")
    private Set<AccommodationOccupancyEntity> occupancies;

    public AccommodationEntity() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Set<ReservationEntity> getReservations() {
        return reservations;
    }

    public void setReservations(Set<ReservationEntity> reservations) {
        this.reservations = reservations;
    }

    public Set<AccommodationOccupancyEntity> getOccupancies() {
        return occupancies;
    }

    public void setOccupancies(Set<AccommodationOccupancyEntity> occupancies) {
        this.occupancies = occupancies;
    }
}
