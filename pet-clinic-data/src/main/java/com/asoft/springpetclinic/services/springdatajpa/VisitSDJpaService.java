package com.asoft.springpetclinic.services.springdatajpa;

import com.asoft.springpetclinic.model.Visit;
import com.asoft.springpetclinic.repositories.VisitRepository;
import com.asoft.springpetclinic.services.VisitService;
import com.asoft.springpetclinic.util.DataUtil;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile("springdatajpa")
public class VisitSDJpaService implements VisitService {
    private final VisitRepository visitRepository;

    public VisitSDJpaService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Override
    public Visit findById(Long aLong) {
        return visitRepository.findById(aLong).orElse(null);
    }

    @Override
    public Visit save(Visit model) {
        return visitRepository.save(model);
    }

    @Override
    public Set<Visit> findAll() {
        return DataUtil.findAllUtil(visitRepository.findAll());
    }

    @Override
    public void delete(Visit model) {
        visitRepository.delete(model);
    }

    @Override
    public void deleteById(Long modelId) {
        visitRepository.deleteById(modelId);
    }
}
