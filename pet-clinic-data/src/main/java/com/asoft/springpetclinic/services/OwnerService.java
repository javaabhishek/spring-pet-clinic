package com.asoft.springpetclinic.services;

import com.asoft.springpetclinic.model.Owner;

public interface OwnerService extends CrudBaseService<Owner,Long> {
    Owner findByLastName(String lastName);
}
