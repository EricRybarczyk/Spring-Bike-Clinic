package dev.ericrybarczyk.springbikeclinic.services.jpa;

import dev.ericrybarczyk.springbikeclinic.model.BikeType;
import dev.ericrybarczyk.springbikeclinic.repositories.BikeTypeRepository;
import dev.ericrybarczyk.springbikeclinic.services.BikeTypeService;
import java.util.HashSet;
import java.util.Set;

public class BikeTypeServiceImpl implements BikeTypeService {

    private final BikeTypeRepository bikeTypeRepository;

    public BikeTypeServiceImpl(BikeTypeRepository bikeTypeRepository) {
        this.bikeTypeRepository = bikeTypeRepository;
    }

    @Override
    public Set<BikeType> findAll() {
        HashSet<BikeType> result = new HashSet<>();
        bikeTypeRepository.findAll().forEach(result::add);
        return result;
    }

    @Override
    public BikeType findById(Long id) {
        return bikeTypeRepository.findById(id).orElse(null);
    }

    @Override
    public BikeType save(BikeType bikeType) {
        return bikeTypeRepository.save(bikeType);
    }

    @Override
    public void delete(BikeType bikeType) {
        bikeTypeRepository.delete(bikeType);
    }

    @Override
    public void deleteById(Long id) {
        bikeTypeRepository.deleteById(id);
    }
}
