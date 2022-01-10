package it.unitn.disi.webarch.mstolin.entities.accommodation;

import it.unitn.disi.webarch.mstolin.entities.occupancy.AccommodationOccupancy;
import it.unitn.disi.webarch.mstolin.entities.reservation.ReservationEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ACCOMMODATION", schema = "PUBLIC", catalog = "ACCOMMODATIONS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DISC", discriminatorType = DiscriminatorType.STRING)
public abstract class AccommodationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false, updatable = false)
    protected int id;

    @Basic
    @Column(name = "NAME", nullable = false)
    protected String name;

    @Basic
    @Column(name = "PRICE", nullable = false)
    protected int price;

    @OneToMany(cascade = { CascadeType.ALL })
    @JoinColumn(name = "ACCOMMODATION_ID")
    private Set<ReservationEntity> reservations;

    @OneToMany(cascade = { CascadeType.ALL })
    @JoinColumn(name = "ACCOMMODATION_ID")
    private Set<AccommodationOccupancy> occupancies;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Set<ReservationEntity> getReservations() {
        return reservations;
    }

    public void setReservations(Set<ReservationEntity> reservations) {
        this.reservations = reservations;
    }

    public Set<AccommodationOccupancy> getOccupancies() {
        return occupancies;
    }

    public void setOccupancies(Set<AccommodationOccupancy> occupancies) {
        this.occupancies = occupancies;
    }
}
