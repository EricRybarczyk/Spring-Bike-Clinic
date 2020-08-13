package dev.ericrybarczyk.springbikeclinic.services.map;

import dev.ericrybarczyk.springbikeclinic.model.BikeType;
import dev.ericrybarczyk.springbikeclinic.services.BikeTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
@Profile({"default","map"})
public class BikeTypeServiceMap extends AbstractMapService<BikeType, Long> implements BikeTypeService {

    @Override
    public Set<BikeType> findAll() {
        return super.findAll();
    }

    @Override
    public BikeType findById(Long id) {
        return super.findById(id);
    }

    @Override
    public BikeType save(BikeType bikeType) {
        return super.save(bikeType);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(BikeType bikeType) {
        super.delete(bikeType);
    }
}
