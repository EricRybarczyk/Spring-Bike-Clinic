package dev.ericrybarczyk.springbikeclinic.repositories;

import dev.ericrybarczyk.springbikeclinic.model.BikeOwner;
import org.springframework.data.repository.CrudRepository;
import java.util.Set;

public interface BikeOwnerRepository extends CrudRepository<BikeOwner, Long> {
    Set<BikeOwner> findByLastName(String lastName);
}
