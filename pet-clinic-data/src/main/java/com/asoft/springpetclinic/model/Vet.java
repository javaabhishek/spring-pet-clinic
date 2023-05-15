package com.asoft.springpetclinic.model;

import jakarta.persistence.Entity;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Vet extends Person{
    private Set<Specialty> specialties=new HashSet<>();

    public Set<Specialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(Set<Specialty> specialties) {
        this.specialties = specialties;
    }
}
