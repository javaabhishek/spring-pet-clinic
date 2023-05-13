package com.asoft.springpetclinic.services.map;

import com.asoft.springpetclinic.model.Specialty;
import com.asoft.springpetclinic.services.SpecialitiesService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SpecialityServiceMap extends AbstractMapService<Specialty,Long> implements SpecialitiesService {


    @Override
    public Specialty findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Specialty save(Specialty model) {
        return super.save(model);
    }

    @Override
    public Set<Specialty> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long modelId) {
        super.deleteById(modelId);
    }

    @Override
    public void delete(Specialty model) {
        super.delete(model);
    }
}
