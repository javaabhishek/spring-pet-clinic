package com.asoft.springpetclinic.services.map;

import com.asoft.springpetclinic.model.Visit;
import com.asoft.springpetclinic.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default","map"})
public class VisitServiceMap extends AbstractMapService<Visit,Long> implements VisitService {
    @Override
    public Visit findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Visit save(Visit model) {
        if(model.getPet()==null || model.getPet().getOwner()==null
        || model.getPet().getId()==null || model.getPet().getOwner().getId()==null)
            throw new RuntimeException("Invalid visit object");
        return super.save(model);
    }

    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long modelId) {
        super.deleteById(modelId);
    }

    @Override
    public void delete(Visit model) {
        super.delete(model);
    }
}
