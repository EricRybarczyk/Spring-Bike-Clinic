package dev.ericrybarczyk.springbikeclinic.repositories;

import dev.ericrybarczyk.springbikeclinic.model.Bike;
import org.springframework.data.repository.CrudRepository;

public interface BikeRepository extends CrudRepository<Bike, Long> {

}
