package it.unitn.disi.webarch.mstolin.webapp.models.accommodation;

import it.unitn.disi.webarch.mstolin.dao.accommodation.AccommodationEntity;

import java.io.Serializable;

public class AccommodationResultDetail implements Serializable {

    AccommodationEntity accommodation;

    double totalPrice;

    double totalPriceExtraHalfBoard;

    public AccommodationResultDetail() {
    }

    public AccommodationResultDetail(AccommodationEntity accommodation, double totalPrice, double totalPriceExtraHalfBoard) {
        this.accommodation = accommodation;
        this.totalPrice = totalPrice;
        this.totalPriceExtraHalfBoard = totalPriceExtraHalfBoard;
    }

    public AccommodationEntity getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(AccommodationEntity accommodation) {
        this.accommodation = accommodation;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTotalPriceExtraHalfBoard() {
        return totalPriceExtraHalfBoard;
    }

    public void setTotalPriceExtraHalfBoard(double totalPriceExtraHalfBoard) {
        this.totalPriceExtraHalfBoard = totalPriceExtraHalfBoard;
    }

}
