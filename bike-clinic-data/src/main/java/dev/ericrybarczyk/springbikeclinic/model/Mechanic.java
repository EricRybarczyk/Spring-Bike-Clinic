package dev.ericrybarczyk.springbikeclinic.model;

import java.util.Set;

public class Mechanic extends Person {

    private Set<Specialty> specialties;

    public Set<Specialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(Set<Specialty> specialties) {
        this.specialties = specialties;
    }
}
