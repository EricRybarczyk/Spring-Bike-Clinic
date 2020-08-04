package dev.ericrybarczyk.springbikeclinic.services.jpa;

import dev.ericrybarczyk.springbikeclinic.model.Mechanic;
import dev.ericrybarczyk.springbikeclinic.repositories.MechanicRepository;
import dev.ericrybarczyk.springbikeclinic.services.MechanicService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class MechanicServiceImpl implements MechanicService {

    private final MechanicRepository mechanicRepository;

    public MechanicServiceImpl(MechanicRepository mechanicRepository) {
        this.mechanicRepository = mechanicRepository;
    }

    @Override
    public Set<Mechanic> findAll() {
        HashSet<Mechanic> result = new HashSet<>();
        mechanicRepository.findAll().forEach(result::add);
        return result;
    }

    @Override
    public Mechanic findById(Long id) {
        return mechanicRepository.findById(id).orElse(null);
    }

    @Override
    public Mechanic save(Mechanic mechanic) {
        return mechanicRepository.save(mechanic);
    }

    @Override
    public void delete(Mechanic mechanic) {
        mechanicRepository.delete(mechanic);
    }

    @Override
    public void deleteById(Long id) {
        mechanicRepository.deleteById(id);
    }
}
