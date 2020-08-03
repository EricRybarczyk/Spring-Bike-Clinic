package dev.ericrybarczyk.springbikeclinic.repositories;

import dev.ericrybarczyk.springbikeclinic.model.BikeOwner;
import org.springframework.data.repository.CrudRepository;

public interface BikeOwnerRepository extends CrudRepository<BikeOwner, Long> {

}
