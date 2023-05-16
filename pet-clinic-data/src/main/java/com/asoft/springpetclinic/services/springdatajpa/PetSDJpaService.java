package com.asoft.springpetclinic.services.springdatajpa;

import com.asoft.springpetclinic.model.Pet;
import com.asoft.springpetclinic.repositories.PetRepository;
import com.asoft.springpetclinic.services.PetService;
import com.asoft.springpetclinic.util.DataUtil;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile("springdatajpa")
public class PetSDJpaService implements PetService {
    private final PetRepository petRepository;

    public PetSDJpaService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Pet findById(Long aLong) {
        return petRepository.findById(aLong).orElse(null);
    }

    @Override
    public Pet save(Pet model) {
        return petRepository.save(model);
    }

    @Override
    public Set<Pet> findAll() {
        return DataUtil.findAllUtil(petRepository.findAll());
    }

    @Override
    public void delete(Pet model) {
        petRepository.delete(model);
    }

    @Override
    public void deleteById(Long modelId) {
        petRepository.deleteById(modelId);
    }
}
