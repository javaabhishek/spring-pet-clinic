package com.asoft.springpetclinic.services.springdatajpa;

import com.asoft.springpetclinic.model.PetType;
import com.asoft.springpetclinic.repositories.PetTypeRepository;
import com.asoft.springpetclinic.services.PetTypeService;
import com.asoft.springpetclinic.util.DataUtil;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class PetTypeSDJpaService implements PetTypeService {

    private final PetTypeRepository petTypeRepository;

    public PetTypeSDJpaService(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public PetType findById(Long aLong) {
        return petTypeRepository.findById(aLong).orElse(null);
    }

    @Override
    public PetType save(PetType model) {
        return petTypeRepository.save(model);
    }

    @Override
    public Set<PetType> findAll() {
        return DataUtil.findAllUtil(petTypeRepository.findAll());
    }

    @Override
    public void delete(PetType model) {
        petTypeRepository.delete(model);
    }

    @Override
    public void deleteById(Long modelId) {
        petTypeRepository.deleteById(modelId);
    }
}
