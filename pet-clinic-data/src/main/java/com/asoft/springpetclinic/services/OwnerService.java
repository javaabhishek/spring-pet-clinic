package com.asoft.springpetclinic.services;

import com.asoft.springpetclinic.model.Owner;
import org.springframework.stereotype.Service;

import java.util.Set;


public interface OwnerService extends CrudBaseService<Owner,Long> {
    Owner findByLastName(String lastName);

    Set<Owner> findAllOwnersByLastNameLike(String anyString);
}
