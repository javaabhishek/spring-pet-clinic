package com.asoft.springpetclinic.services;

import com.asoft.springpetclinic.model.Owner;
import org.springframework.stereotype.Service;

@Service
public interface OwnerService extends CrudBaseService<Owner,Long> {
    Owner findByLastName(String lastName);
}
