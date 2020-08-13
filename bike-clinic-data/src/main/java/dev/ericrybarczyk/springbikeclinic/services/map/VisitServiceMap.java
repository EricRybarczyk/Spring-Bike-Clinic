package dev.ericrybarczyk.springbikeclinic.services.map;

import dev.ericrybarczyk.springbikeclinic.model.Visit;
import dev.ericrybarczyk.springbikeclinic.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
@Profile({"default","map"})
public class VisitServiceMap extends AbstractMapService<Visit, Long> implements VisitService {

    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public Visit findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Visit save(Visit visit) {
        //make sure child objects are instantiated and are already persisted
        if (visit.getBike() == null || visit.getBike().getOwner() == null
                || visit.getBike().getId() == null || visit.getBike().getOwner().getId() == null) {
            throw new RuntimeException("Invalid Visit");
        }
        return super.save(visit);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Visit visit) {
        super.delete(visit);
    }
}
