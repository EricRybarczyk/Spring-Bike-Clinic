package dev.ericrybarczyk.springbikeclinic.repositories;

import dev.ericrybarczyk.springbikeclinic.model.Mechanic;
import org.springframework.data.repository.CrudRepository;

public interface MechanicRepository extends CrudRepository<Mechanic, Long> {

}
