package dev.ericrybarczyk.springbikeclinic.repositories;

import dev.ericrybarczyk.springbikeclinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> {

}
