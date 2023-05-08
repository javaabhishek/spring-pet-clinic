package com.asoft.springpetclinic.services.map;

import com.asoft.springpetclinic.model.Owner;
import com.asoft.springpetclinic.services.OwnerService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner,Long> implements OwnerService {

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner owner) {
        return super.save(owner);
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
