package dev.ericrybarczyk.springbikeclinic.repositories;

import dev.ericrybarczyk.springbikeclinic.model.BikeType;
import org.springframework.data.repository.CrudRepository;

public interface BikeTypeRepository extends CrudRepository<BikeType, Long> {

}
