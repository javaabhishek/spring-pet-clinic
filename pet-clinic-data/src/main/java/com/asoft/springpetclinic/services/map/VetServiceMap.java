package com.asoft.springpetclinic.services.map;

import com.asoft.springpetclinic.model.Specialty;
import com.asoft.springpetclinic.model.Vet;
import com.asoft.springpetclinic.services.SpecialityService;
import com.asoft.springpetclinic.services.VetService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VetServiceMap extends AbstractMapService<Vet,Long> implements VetService {

    private final SpecialityService specialitiesService;

    public VetServiceMap(SpecialityService specialitiesService) {
        this.specialitiesService = specialitiesService;
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Vet save(Vet vet) {
        if(vet!=null){
            if(vet.getSpecialties().size()>0){
                vet.getSpecialties().forEach(specialty -> {
                    if(specialty.getId()==null){
                        Specialty savedSpecility= specialitiesService.save(specialty);
                        specialty.setId(savedSpecility.getId());
                    }
                });
            }else{
                throw new RuntimeException("Must required Specialities");
            }
        }else{
            return null;
        }
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