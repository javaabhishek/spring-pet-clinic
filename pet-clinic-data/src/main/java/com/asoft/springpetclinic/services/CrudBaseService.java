package com.asoft.springpetclinic.services;

import java.util.Set;

public interface CrudBaseService<T,ID> {
    T findById(ID id);

    T save(T owner);

    Set<T> findAll();

    void delete(T model);

    void deleteById(ID modelId);
}
