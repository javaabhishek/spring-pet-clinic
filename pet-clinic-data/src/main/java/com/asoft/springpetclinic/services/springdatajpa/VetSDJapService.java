package com.asoft.springpetclinic.services.springdatajpa;

import com.asoft.springpetclinic.model.Vet;
import com.asoft.springpetclinic.repositories.VetRepository;
import com.asoft.springpetclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class VetSDJapService implements VetService {

    private final VetRepository vetRepository;

    public VetSDJapService(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    public Vet findById(Long aLong) {
        return vetRepository.findById(aLong).orElse(null);
    }

    @Override
    public Vet save(Vet model) {
        return vetRepository.save(model);
    }

    @Override
    public Set<Vet> findAll() {
        Set<Vet> lstOfVets=new HashSet<>();
        vetRepository.findAll().forEach(lstOfVets::add);
        return lstOfVets;
    }

    @Override
    public void delete(Vet model) {
        vetRepository.delete(model);
    }

    @Override
    public void deleteById(Long modelId) {
       vetRepository.deleteById(modelId);
    }
}
