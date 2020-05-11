package dev.ericrybarczyk.springbikeclinic.services;

import dev.ericrybarczyk.springbikeclinic.model.Mechanic;
import java.util.Set;

public interface MechanicService {
    Mechanic findById(Long id);
    Mechanic save(Mechanic mechanic);
    Set<Mechanic> findAll();
}
