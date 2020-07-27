package dev.ericrybarczyk.springbikeclinic.model;

public class BikeType extends BaseEntity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
