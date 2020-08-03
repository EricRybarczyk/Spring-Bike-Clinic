package dev.ericrybarczyk.springbikeclinic.repositories;

import dev.ericrybarczyk.springbikeclinic.model.Specialty;
import org.springframework.data.repository.CrudRepository;

public interface SpecialtyRepository extends CrudRepository<Specialty, Long> {

}
