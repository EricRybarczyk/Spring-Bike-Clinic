package dev.ericrybarczyk.springbikeclinic.services.jpa;

import dev.ericrybarczyk.springbikeclinic.model.Bike;
import dev.ericrybarczyk.springbikeclinic.repositories.BikeRepository;
import dev.ericrybarczyk.springbikeclinic.services.BikeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class BikeServiceImpl implements BikeService {

    private final BikeRepository bikeRepository;

    public BikeServiceImpl(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    @Override
    public Set<Bike> findAll() {
        Set<Bike> result = new HashSet<>();
        bikeRepository.findAll().forEach(result::add);
        return result;
    }

    @Override
    public Bike findById(Long id) {
        return bikeRepository.findById(id).orElse(null);
    }

    @Override
    public Bike save(Bike bike) {
        return bikeRepository.save(bike);
    }

    @Override
    public void delete(Bike bike) {
        bikeRepository.delete(bike);
    }

    @Override
    public void deleteById(Long id) {
        bikeRepository.deleteById(id);
    }
}
