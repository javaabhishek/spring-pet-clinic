package com.asoft.springpetclinic.services.map;

import com.asoft.springpetclinic.model.Speciality;
import com.asoft.springpetclinic.services.SpecialityService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default","map"})
public class SpecialityServiceMap extends AbstractMapService<Speciality,Long> implements SpecialityService {


    @Override
    public Speciality findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Speciality save(Speciality model) {
        return super.save(model);
    }

    @Override
    public Set<Speciality> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long modelId) {
        super.deleteById(modelId);
    }

    @Override
    public void delete(Speciality model) {
        super.delete(model);
    }
}
