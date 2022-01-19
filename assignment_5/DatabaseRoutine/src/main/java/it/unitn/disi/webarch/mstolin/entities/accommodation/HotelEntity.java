package it.unitn.disi.webarch.mstolin.entities.accommodation;

import javax.persistence.*;

@Entity()
@DiscriminatorValue("HOTEL")
public class HotelEntity extends AccommodationEntity {

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
