package dev.ericrybarczyk.springbikeclinic.services.map;

import dev.ericrybarczyk.springbikeclinic.model.Mechanic;
import dev.ericrybarczyk.springbikeclinic.services.CrudService;
import java.util.Set;

public class MechanicServiceMap extends AbstractMapService<Mechanic, Long> implements CrudService<Mechanic, Long> {
    @Override
    public Set<Mechanic> findAll() {
        return super.findAll();
    }

    @Override
    public Mechanic findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Mechanic save(Mechanic mechanic) {
        return super.save(mechanic.getId(), mechanic);
    }

    @Override
    public void delete(Mechanic mechanic) {
        super.delete(mechanic);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
