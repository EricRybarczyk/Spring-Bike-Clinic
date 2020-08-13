package dev.ericrybarczyk.springbikeclinic.services.map;

import dev.ericrybarczyk.springbikeclinic.model.Bike;
import dev.ericrybarczyk.springbikeclinic.model.BikeOwner;
import dev.ericrybarczyk.springbikeclinic.services.BikeOwnerService;
import dev.ericrybarczyk.springbikeclinic.services.BikeService;
import dev.ericrybarczyk.springbikeclinic.services.BikeTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Profile({"default","map"})
public class BikeOwnerServiceMap extends AbstractMapService<BikeOwner, Long> implements BikeOwnerService {

    private final BikeTypeService bikeTypeService;
    private final BikeService bikeService;

    public BikeOwnerServiceMap(BikeTypeService bikeTypeService, BikeService bikeService) {
        this.bikeTypeService = bikeTypeService;
        this.bikeService = bikeService;
    }

    @Override
    public Optional<BikeOwner> findByLastName(String lastName) {
        return super.findAll().stream()
                .filter(e -> e.getLastName().equalsIgnoreCase(lastName))
                .findFirst();
    }

    @Override
    public List<BikeOwner> findAllByLastName(String lastName) {
        return super.findAll().stream()
                .filter(e -> e.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
    }

    @Override
    public Set<BikeOwner> findAll() {
        return super.findAll();
    }

    @Override
    public BikeOwner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public BikeOwner save(BikeOwner bikeOwner) {
        if (bikeOwner == null) { return null; }
        // save bikes for the owner
        if (bikeOwner.getBikes() != null) {
            bikeOwner.getBikes().forEach( bike -> {
                if(bike.getBikeType() == null) { throw new RuntimeException("BikeType is required."); }
                // if no id then we have to save it so the id gets generated, and put the saved object
                // back into the object we are working with so it will have the generated id value.
                if (bike.getBikeType().getId() == null) {
                    bike.setBikeType(bikeTypeService.save(bike.getBikeType()));
                }
                // same goal for the bike object, but we save the bike and then use that reference to
                // store back the id value.
                if (bike.getId() == null) {
                    Bike savedBike = bikeService.save(bike);
                    bike.setId(savedBike.getId());
                }
            });
        }
        return super.save(bikeOwner);
    }

    @Override
    public void delete(BikeOwner bikeOwner) {
        super.delete(bikeOwner);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

}
