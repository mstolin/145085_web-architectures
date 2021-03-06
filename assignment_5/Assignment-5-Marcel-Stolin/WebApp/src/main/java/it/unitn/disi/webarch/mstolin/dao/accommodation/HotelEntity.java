package it.unitn.disi.webarch.mstolin.dao.accommodation;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity()
@DiscriminatorValue("HOTEL")
public class HotelEntity extends AccommodationEntity implements Serializable {

    @Basic
    @Column(name = "EXTRA_HALF_BOARD", nullable = true)
    private Double extraHalfBoard;

    @Basic
    @Column(name = "STARS", nullable = true)
    private Integer stars;

    @Basic
    @Column(name = "PLACES", nullable = true)
    private Integer places;

    public HotelEntity(String name, Double price, Double extraHalfBoard, int stars, int places) {
        super();
        this.name = name;
        this.price = price;
        this.extraHalfBoard = extraHalfBoard;
        this.stars = stars;
        this.places = places;
    }

    public HotelEntity() {
        super();
    }

    public Double getExtraHalfBoard() {
        return extraHalfBoard;
    }

    public void setExtraHalfBoard(Double extraHalfBoard) {
        this.extraHalfBoard = extraHalfBoard;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public Integer getPlaces() {
        return places;
    }

    public void setPlaces(Integer places) {
        this.places = places;
    }

}
