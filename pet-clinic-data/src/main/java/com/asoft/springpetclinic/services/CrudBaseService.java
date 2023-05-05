package com.asoft.springpetclinic.services;

import java.util.Set;

public interface CrudBaseService<T,ID> {
    T findById(T id);

    T save(T owner);

    Set<T> findAll();

    void delete(T model);

    void deleteById(T modelId);
}
