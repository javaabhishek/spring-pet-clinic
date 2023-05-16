package com.asoft.springpetclinic.services.springdatajpa;

import com.asoft.springpetclinic.model.Speciality;
import com.asoft.springpetclinic.repositories.SpecialityRepository;
import com.asoft.springpetclinic.services.SpecialityService;
import com.asoft.springpetclinic.util.DataUtil;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile("springdatajpa")
public class SpecialitySDJpaService implements SpecialityService {
    private final SpecialityRepository specialityRepository;

    public SpecialitySDJpaService(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

    @Override
    public Speciality findById(Long aLong) {
        return specialityRepository.findById(aLong).orElse(null);
    }

    @Override
    public Speciality save(Speciality model) {
        return specialityRepository.save(model);
    }

    @Override
    public Set<Speciality> findAll() {
        return DataUtil.findAllUtil(specialityRepository.findAll());
    }

    @Override
    public void delete(Speciality model) {
        specialityRepository.delete(model);
    }

    @Override
    public void deleteById(Long modelId) {
        specialityRepository.deleteById(modelId);
    }
}
