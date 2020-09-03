package dev.ericrybarczyk.springbikeclinic.services.jpa;

import dev.ericrybarczyk.springbikeclinic.model.BikeOwner;
import dev.ericrybarczyk.springbikeclinic.repositories.BikeOwnerRepository;
import dev.ericrybarczyk.springbikeclinic.repositories.BikeRepository;
import dev.ericrybarczyk.springbikeclinic.repositories.BikeTypeRepository;
import dev.ericrybarczyk.springbikeclinic.services.BikeOwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class BikeOwnerServiceImpl implements BikeOwnerService {

    private final BikeOwnerRepository bikeOwnerRepository;
    private final BikeRepository bikeRepository;
    private final BikeTypeRepository bikeTypeRepository;

    public BikeOwnerServiceImpl(BikeOwnerRepository bikeOwnerRepository, BikeRepository bikeRepository, BikeTypeRepository bikeTypeRepository) {
        this.bikeOwnerRepository = bikeOwnerRepository;
        this.bikeRepository = bikeRepository;
        this.bikeTypeRepository = bikeTypeRepository;
    }

    @Override
    public Optional<BikeOwner> findByLastName(String lastName) {
        return bikeOwnerRepository.findByLastName(lastName);
    }

    @Override
    public List<BikeOwner> findAllByLastName(String lastName) {
        return bikeOwnerRepository.findAllByLastName(lastName);
    }

    @Override
    public List<BikeOwner> findAllByLastNameLikeIgnoreCase(String lastName) {
        return bikeOwnerRepository.findAllByLastNameLikeIgnoreCase(lastName);
    }

    @Override
    public Set<BikeOwner> findAll() {
        Set<BikeOwner> owners = new HashSet<>();
        bikeOwnerRepository.findAll().forEach(owners::add);
        return owners;
    }

    @Override
    public BikeOwner findById(Long id) {
        return bikeOwnerRepository.findById(id).orElse(null);
    }

    @Override
    public BikeOwner save(BikeOwner bikeOwner) {
        return bikeOwnerRepository.save(bikeOwner);
    }

    @Override
    public void delete(BikeOwner bikeOwner) {
        bikeOwnerRepository.delete(bikeOwner);
    }

    @Override
    public void deleteById(Long id) {
        bikeOwnerRepository.deleteById(id);
    }
}
