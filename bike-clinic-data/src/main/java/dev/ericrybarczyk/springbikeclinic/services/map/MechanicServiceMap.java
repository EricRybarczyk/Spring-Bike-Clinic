package dev.ericrybarczyk.springbikeclinic.services.map;

import dev.ericrybarczyk.springbikeclinic.model.Mechanic;
import dev.ericrybarczyk.springbikeclinic.model.Specialty;
import dev.ericrybarczyk.springbikeclinic.services.MechanicService;
import dev.ericrybarczyk.springbikeclinic.services.SpecialtyService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
@Profile({"default","map"})
public class MechanicServiceMap extends AbstractMapService<Mechanic, Long> implements MechanicService {

    private final SpecialtyService specialtyService;

    public MechanicServiceMap(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

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
        mechanic.getSpecialties().forEach( specialty -> {
            if (specialty.getId() == null) {
                Specialty savedSpecialty = specialtyService.save(specialty);
                specialty.setId(savedSpecialty.getId());
            }
        });
        return super.save(mechanic);
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
