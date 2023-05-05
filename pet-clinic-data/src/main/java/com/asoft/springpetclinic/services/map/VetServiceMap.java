package com.asoft.springpetclinic.services.map;

import com.asoft.springpetclinic.model.Vet;
import com.asoft.springpetclinic.services.CrudBaseService;

import java.util.Set;

public class VetServiceMap extends AbstractMapService<Vet,Long> implements CrudBaseService<Vet,Long> {

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Vet save(Vet vet) {
        return super.save(vet.getId(),vet);
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long modelId) {
        super.deleteById(modelId);
    }

    @Override
    public void delete(Vet model) {
        super.delete(model);
    }
}