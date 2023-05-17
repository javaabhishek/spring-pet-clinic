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
@Builder
//@EqualsAndHashCode(exclude = {"petType","owner","visits"})
@Entity
@Table(name = "pets")
public class Pet extends BaseEntity{

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
