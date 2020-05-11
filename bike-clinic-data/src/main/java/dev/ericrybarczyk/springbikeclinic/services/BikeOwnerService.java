package dev.ericrybarczyk.springbikeclinic.services;

import dev.ericrybarczyk.springbikeclinic.model.BikeOwner;
import java.util.Set;

public interface BikeOwnerService {

    BikeOwner findById(Long id);
    Set<BikeOwner> findByLastName(String lastName);
    BikeOwner save(BikeOwner bikeOwner);
    Set<BikeOwner> findAll();
}
