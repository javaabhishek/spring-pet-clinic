package com.asoft.springpetclinic.services.springdatajpa;

import com.asoft.springpetclinic.model.Owner;
import com.asoft.springpetclinic.repositories.OwnerRepository;
import com.asoft.springpetclinic.repositories.PetRepository;
import com.asoft.springpetclinic.repositories.PetTypeRepository;
import com.asoft.springpetclinic.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class OwnerSDJpaService implements OwnerService {
    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;
    private final PetTypeRepository petTypeRepository;

    public OwnerSDJpaService(OwnerRepository ownerRepository, PetRepository petRepository,
                             PetTypeRepository petTypeRepository) {
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public Owner findById(Long aLong) {
        return ownerRepository.findById(aLong).orElse(null);
    }

    @Override
    public Owner save(Owner model) {
        return ownerRepository.save(model);
    }

    @Override
    public Set<Owner> findAll() {
        Set<Owner> lstOfOwners=new HashSet<>();
        ownerRepository.findAll().forEach(lstOfOwners::add);
        return lstOfOwners;
    }

    @Override
    public void delete(Owner model) {
        ownerRepository.delete(model);
    }

    @Override
    public void deleteById(Long modelId) {
        ownerRepository.deleteById(modelId);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName).orElse(null);
    }

    @Override
    public Set<Owner> findAllOwnersByLastNameLike(String lastName) {
        return ownerRepository.findAllByLastNameLike(lastName);
    }
}
