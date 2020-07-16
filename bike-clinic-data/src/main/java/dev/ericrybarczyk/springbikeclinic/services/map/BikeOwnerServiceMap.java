package dev.ericrybarczyk.springbikeclinic.services.map;

import dev.ericrybarczyk.springbikeclinic.model.BikeOwner;
import dev.ericrybarczyk.springbikeclinic.services.BikeOwnerService;
import java.util.Set;

public class BikeOwnerServiceMap extends AbstractMapService<BikeOwner, Long> implements BikeOwnerService {

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
        return super.save(bikeOwner.getId(), bikeOwner);
    }

    @Override
    public void delete(BikeOwner bikeOwner) {
        super.delete(bikeOwner);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Set<BikeOwner> findByLastName(String lastName) {
        return null; // TODO: implementation
    }
}
