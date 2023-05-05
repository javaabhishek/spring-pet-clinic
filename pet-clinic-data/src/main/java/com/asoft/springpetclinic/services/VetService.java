package com.asoft.springpetclinic.services;

import com.asoft.springpetclinic.model.Pet;
import com.asoft.springpetclinic.model.Vet;

import java.util.Set;

public interface VetService {
    Vet findById(Long id);

    Vet save(Vet vet);

    Set<Vet> findAll();
}
