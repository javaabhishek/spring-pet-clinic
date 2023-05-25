package com.asoft.springpetclinic.repositories;

import com.asoft.springpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface OwnerRepository extends CrudRepository<Owner,Long> {
    Optional<Owner> findByLastName(String lastName);

    Set<Owner> findAllByLastNameLike(String lastName);
}
