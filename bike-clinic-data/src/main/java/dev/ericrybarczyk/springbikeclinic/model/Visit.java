package dev.ericrybarczyk.springbikeclinic.model;

import java.time.LocalDate;

public class Visit extends BaseEntity {

    private LocalDate date;
    private String description;
    private Bike bike;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bike getBike() {
        return bike;
    }

    public void setBike(Bike bike) {
        this.bike = bike;
    }
}
