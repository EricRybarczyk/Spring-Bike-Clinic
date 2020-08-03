package dev.ericrybarczyk.springbikeclinic.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bikes")
public class Bike extends BaseEntity {

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "bike_type_id")
    private BikeType bikeType;

    @ManyToOne
    @JoinColumn(name = "bike_owner_id")
    private BikeOwner owner;

    @Column(name = "purchase_date")
    private LocalDate purchaseDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bike")
    private Set<Visit> visits = new HashSet<>();


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

    public Set<Visit> getVisits() {
        return visits;
    }

    public void setVisits(Set<Visit> visits) {
        this.visits = visits;
    }
}
