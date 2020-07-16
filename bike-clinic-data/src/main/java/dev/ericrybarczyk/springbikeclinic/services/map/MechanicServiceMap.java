package dev.ericrybarczyk.springbikeclinic.services.map;

import dev.ericrybarczyk.springbikeclinic.model.Mechanic;
import dev.ericrybarczyk.springbikeclinic.services.MechanicService;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class MechanicServiceMap extends AbstractMapService<Mechanic, Long> implements MechanicService {

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
