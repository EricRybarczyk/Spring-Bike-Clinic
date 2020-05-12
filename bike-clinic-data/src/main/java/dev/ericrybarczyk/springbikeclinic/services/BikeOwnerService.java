package dev.ericrybarczyk.springbikeclinic.services;

import dev.ericrybarczyk.springbikeclinic.model.BikeOwner;
import java.util.Set;

public interface BikeOwnerService extends CrudService<BikeOwner, Long> {
    Set<BikeOwner> findByLastName(String lastName);
}
