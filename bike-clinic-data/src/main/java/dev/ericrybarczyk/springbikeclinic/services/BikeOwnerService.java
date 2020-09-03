package dev.ericrybarczyk.springbikeclinic.services;

import dev.ericrybarczyk.springbikeclinic.model.BikeOwner;
import java.util.List;
import java.util.Optional;

public interface BikeOwnerService extends CrudService<BikeOwner, Long> {
    Optional<BikeOwner> findByLastName(String lastName); // findBy presumes the matching criteria is unique
    List<BikeOwner> findAllByLastName(String lastName);  // findAllBy is used when there can be multiple result entities
    List<BikeOwner> findAllByLastNameLikeIgnoreCase(String anyString);

}
