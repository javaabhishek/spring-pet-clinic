package com.asoft.springpetclinic.services.map;

import com.asoft.springpetclinic.model.Vet;
import com.asoft.springpetclinic.services.CrudBaseService;
import com.asoft.springpetclinic.services.VetService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VetServiceMap extends AbstractMapService<Vet,Long> implements VetService {

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Vet save(Vet vet) {
        return super.save(vet);
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