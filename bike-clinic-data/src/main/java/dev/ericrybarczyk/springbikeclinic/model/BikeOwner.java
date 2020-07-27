package dev.ericrybarczyk.springbikeclinic.model;

import java.util.Set;

public class BikeOwner extends Person {

    private Set<Bike> bikes;

    public Set<Bike> getBikes() {
        return bikes;
    }

    public void setBikes(Set<Bike> bikes) {
        this.bikes = bikes;
    }
}
