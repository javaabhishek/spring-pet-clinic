package com.asoft.springpetclinic.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(exclude = {"petType","owner","visits"})
@Entity
@Table(name = "pets")
public class Pet extends BaseEntity{
    @Builder
    public Pet(Long id, String petName, PetType petType, Owner owner, LocalDate birthDate, Set<Visit> visits) {
        super(id);
        this.petName = petName;
        this.petType = petType;
        this.owner = owner;
        this.birthDate = birthDate;
        if(visits!=null){
            this.visits = visits;
        }
    }

    @Column(name="pet_name")
    private String petName;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private PetType  petType;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "pet")
    private Set<Visit> visits=new HashSet<>();
}
