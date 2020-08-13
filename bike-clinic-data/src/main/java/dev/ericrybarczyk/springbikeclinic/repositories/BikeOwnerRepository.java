package dev.ericrybarczyk.springbikeclinic.repositories;

import dev.ericrybarczyk.springbikeclinic.model.BikeOwner;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface BikeOwnerRepository extends CrudRepository<BikeOwner, Long> {
    Optional<BikeOwner> findByLastName(String lastName);
    List<BikeOwner> findAllByLastName(String lastName);
}
