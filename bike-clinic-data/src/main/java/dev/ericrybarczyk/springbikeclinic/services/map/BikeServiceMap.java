package dev.ericrybarczyk.springbikeclinic.services.map;

import dev.ericrybarczyk.springbikeclinic.model.Bike;
import dev.ericrybarczyk.springbikeclinic.services.BikeService;
import java.util.Set;

public class BikeServiceMap extends AbstractMapService<Bike, Long> implements BikeService {
    @Override
    public Set<Bike> findAll() {
        return super.findAll();
    }

    @Override
    public Bike findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Bike save(Bike bike) {
        return super.save(bike.getId(), bike);
    }

    @Override
    public void delete(Bike bike) {
        super.delete(bike);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
