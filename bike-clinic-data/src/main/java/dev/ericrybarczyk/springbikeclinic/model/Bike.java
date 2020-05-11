package dev.ericrybarczyk.springbikeclinic.model;

import java.time.LocalDate;

public class Bike extends BaseEntity {
    private BikeType bikeType;
    private BikeOwner owner;
    private LocalDate purchaseDate;

    public BikeType getBikeType() {
        return bikeType;
    }

    public void setBikeType(BikeType bikeType) {
        this.bikeType = bikeType;
    }

    public BikeOwner getOwner() {
        return owner;
    }

    public void setOwner(BikeOwner owner) {
        this.owner = owner;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
