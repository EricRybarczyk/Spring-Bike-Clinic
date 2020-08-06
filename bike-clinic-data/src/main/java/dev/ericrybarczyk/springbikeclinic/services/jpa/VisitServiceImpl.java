package dev.ericrybarczyk.springbikeclinic.services.jpa;

import dev.ericrybarczyk.springbikeclinic.model.Visit;
import dev.ericrybarczyk.springbikeclinic.repositories.VisitRepository;
import dev.ericrybarczyk.springbikeclinic.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class VisitServiceImpl implements VisitService {

    private final VisitRepository visitRepository;

    public VisitServiceImpl(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Override
    public Set<Visit> findAll() {
        Set<Visit> result = new HashSet<>();
        visitRepository.findAll().forEach(result::add);
        return result;
    }

    @Override
    public Visit findById(Long id) {
        return visitRepository.findById(id).orElse(null);
    }

    @Override
    public Visit save(Visit visit) {
        return visitRepository.save(visit);
    }

    @Override
    public void delete(Visit visit) {
        visitRepository.delete(visit);
    }

    @Override
    public void deleteById(Long id) {
        visitRepository.deleteById(id);
    }
}
