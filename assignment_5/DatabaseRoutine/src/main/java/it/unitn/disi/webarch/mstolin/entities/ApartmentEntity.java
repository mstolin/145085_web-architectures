package it.unitn.disi.webarch.mstolin.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Entity()
@DiscriminatorValue("APARTMENT")
public class ApartmentEntity extends AccommodationEntity implements Serializable {
    @Basic
    @Column(name = "FINAL_CLEANING_FEE", nullable = true)
    private Integer finalCleaningFee;

    @Basic
    @Column(name = "MAX_PERSONS", nullable = true)
    private Integer maxPersons;

    @OneToMany(cascade = { CascadeType.ALL })
    @JoinColumn(name = "ACCOMMODATION_ID")
    private Set<ReservationEntity> reservations;

    public ApartmentEntity(String name, int price, int finalCleaningFee, int maxPersons) {
        super();
        this.name = name;
        this.price = price;
        this.finalCleaningFee = finalCleaningFee;
        this.maxPersons = maxPersons;
    }

    public ApartmentEntity() {
        super();
    }

    public Integer getFinalCleaningFee() {
        return finalCleaningFee;
    }

    public void setFinalCleaningFee(Integer finalCleaningFee) {
        this.finalCleaningFee = finalCleaningFee;
    }

    public Integer getMaxPersons() {
        return maxPersons;
    }

    public void setMaxPersons(Integer maxPersons) {
        this.maxPersons = maxPersons;
    }

    public Set<ReservationEntity> getReservations() {
        return reservations;
    }

    public void setReservations(Set<ReservationEntity> reservations) {
        this.reservations = reservations;
    }
}
