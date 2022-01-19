package it.unitn.disi.webarch.mstolin.dao.accommodation;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;


@Entity()
@DiscriminatorValue("APARTMENT")
public class ApartmentEntity extends AccommodationEntity implements Serializable {
    @Basic
    @Column(name = "FINAL_CLEANING_FEE", nullable = true)
    private Double finalCleaningFee;

    @Basic
    @Column(name = "MAX_PERSONS", nullable = true)
    private Integer maxPersons;

    public ApartmentEntity(String name, Double price, Double finalCleaningFee, int maxPersons) {
        super();
        this.name = name;
        this.price = price;
        this.finalCleaningFee = finalCleaningFee;
        this.maxPersons = maxPersons;
    }

    public ApartmentEntity() {
        super();
    }

    public Double getFinalCleaningFee() {
        return finalCleaningFee;
    }

    public void setFinalCleaningFee(Double finalCleaningFee) {
        this.finalCleaningFee = finalCleaningFee;
    }

    public Integer getMaxPersons() {
        return maxPersons;
    }

    public void setMaxPersons(Integer maxPersons) {
        this.maxPersons = maxPersons;
    }
}
