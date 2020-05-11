package dev.ericrybarczyk.springbikeclinic.services;

import dev.ericrybarczyk.springbikeclinic.model.Bike;
import java.util.Set;

public interface BikeService {
    Bike findById(Long id);
    Bike save(Bike bike);
    Set<Bike> findAll();
}
