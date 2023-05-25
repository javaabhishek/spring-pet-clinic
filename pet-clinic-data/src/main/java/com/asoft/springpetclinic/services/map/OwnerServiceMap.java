package com.asoft.springpetclinic.services.map;

import com.asoft.springpetclinic.model.Owner;
import com.asoft.springpetclinic.model.Pet;
import com.asoft.springpetclinic.services.OwnerService;
import com.asoft.springpetclinic.services.PetService;
import com.asoft.springpetclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default","map"})
public class OwnerServiceMap extends AbstractMapService<Owner,Long> implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerServiceMap(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner owner) {

        if(owner!=null){
            if(owner.getPets()!=null){
                owner.getPets().forEach(pet->{
                    if(pet.getPetType()!=null){
                        if(pet.getPetType().getId()==null){
                            //save pet type
                            pet.setPetType(petTypeService.save(pet.getPetType()));
                        }
                        if(pet.getId()==null){
                            Pet savedPet=petService.save(pet);
                            pet.setId(savedPet.getId());
                        }
                    }else{
                        throw new RuntimeException("Pet Type Must Required");
                    }
                });
            }
            return super.save(owner);
        }else{
         return null;
        }

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
        return super.findAll()
                .stream()
                .filter(ele->ele.getLastName().equalsIgnoreCase(lastName))
                .findFirst().orElse(null);
    }

    @Override
    public Set<Owner> findAllOwnersByLastNameLike(String anyString) {
        return null;
    }
}
