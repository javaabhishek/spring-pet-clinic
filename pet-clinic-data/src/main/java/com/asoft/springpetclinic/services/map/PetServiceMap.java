package com.asoft.springpetclinic.services.map;

import com.asoft.springpetclinic.model.Pet;
import com.asoft.springpetclinic.services.CrudBaseService;
import com.asoft.springpetclinic.services.PetService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PetServiceMap extends AbstractMapService<Pet,Long> implements PetService {

    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Pet save(Pet pet) {
        return super.save(pet.getId(),pet);
    }

    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long modelId) {
        super.deleteById(modelId);
    }

    @Override
    public void delete(Pet model) {
        super.delete(model);
    }
}
