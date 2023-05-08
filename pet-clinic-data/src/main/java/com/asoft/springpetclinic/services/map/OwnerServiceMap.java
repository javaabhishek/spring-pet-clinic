package com.asoft.springpetclinic.services.map;

import com.asoft.springpetclinic.model.Owner;
import com.asoft.springpetclinic.services.OwnerService;

import java.util.Set;

public class OwnerServiceMap extends AbstractMapService<Owner,Long> implements OwnerService {

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner owner) {
        return super.save(owner.getId(),owner);
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long modelId) {
        super.deleteById(modelId);
    }

    @Override
    public void delete(Owner model) {
        super.delete(model);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }
}
