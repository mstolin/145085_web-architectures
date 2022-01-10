package it.unitn.disi.webarch.mstolin.entities.accommodation;

import it.unitn.disi.webarch.mstolin.entities.occupancy.AccommodationOccupancy;
import it.unitn.disi.webarch.mstolin.entities.reservation.ReservationEntity;

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
    protected int price;

    @OneToMany(cascade = { CascadeType.ALL })
    @JoinColumn(name = "ACCOMMODATION_ID")
    private Set<ReservationEntity> reservations;

    @OneToMany(cascade = { CascadeType.ALL })
    @JoinColumn(name = "ACCOMMODATION_ID")
    private Set<AccommodationOccupancy> occupancies;

    public AccommodationEntity() {
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

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccommodationEntity that = (AccommodationEntity) o;

        if (id != that.id) return false;
        if (price != that.price) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (finalCleaningFee != null ? !finalCleaningFee.equals(that.finalCleaningFee) : that.finalCleaningFee != null)
            return false;
        if (maxPersons != null ? !maxPersons.equals(that.maxPersons) : that.maxPersons != null) return false;
        if (extraHalfBoard != null ? !extraHalfBoard.equals(that.extraHalfBoard) : that.extraHalfBoard != null)
            return false;
        if (stars != null ? !stars.equals(that.stars) : that.stars != null) return false;
        return places != null ? places.equals(that.places) : that.places == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + (finalCleaningFee != null ? finalCleaningFee.hashCode() : 0);
        result = 31 * result + (maxPersons != null ? maxPersons.hashCode() : 0);
        result = 31 * result + (extraHalfBoard != null ? extraHalfBoard.hashCode() : 0);
        result = 31 * result + (stars != null ? stars.hashCode() : 0);
        result = 31 * result + (places != null ? places.hashCode() : 0);
        return result;
    }*/
}
