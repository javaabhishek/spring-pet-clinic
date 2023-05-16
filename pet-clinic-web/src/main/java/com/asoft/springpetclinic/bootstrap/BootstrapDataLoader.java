package com.asoft.springpetclinic.bootstrap;

import com.asoft.springpetclinic.model.*;
import com.asoft.springpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class BootstrapDataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialitiesService;
    private final VisitService visitService;
    private final PetService petService;



    public BootstrapDataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
                               SpecialityService specialitiesService, VisitService visitService, PetService petService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialitiesService = specialitiesService;
        this.visitService = visitService;
        this.petService = petService;
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {
        PetType dog=new PetType();
        dog.setName("Dog");
        PetType savedDogPetType=petTypeService.save(dog);

        PetType cat=new PetType();
        cat.setName("Cat");
        PetType savedCatPetType=petTypeService.save(cat);

        Owner onr1=new Owner();
        onr1.setFirstName("Abhishek");
        onr1.setLastName("Patil");
        onr1.setCity("Kolhapur");
        onr1.setAddress("Kolhapur");
        onr1.setTelephone("8657888773");

        Pet abhishekPetDog=new Pet();
        abhishekPetDog.setPetName("BhuBhu");
        abhishekPetDog.setOwner(onr1);
        abhishekPetDog.setPetType(savedDogPetType);
        abhishekPetDog.setBirthDate(LocalDate.now());
        onr1.getPets().add(abhishekPetDog);


        ownerService.save(onr1);

        Visit visitDog=new Visit();
        visitDog.setDescription("Sneeze Dog");
        visitDog.setPet(abhishekPetDog);
        visitDog.setVisitDate(LocalDate.now());
        visitService.save(visitDog);

        Owner onr2=new Owner();
        onr2.setFirstName("Tejal");
        onr2.setLastName("Patil");
        onr2.setCity("Pune");
        onr2.setAddress("Pune");
        onr2.setTelephone("8657888773");

        Pet tejalPetCat=new Pet();
        tejalPetCat.setPetName("MauMau");
        tejalPetCat.setPetType(savedCatPetType);
        tejalPetCat.setOwner(onr2);
        tejalPetCat.setBirthDate(LocalDate.now());
        onr2.getPets().add(tejalPetCat);
        ownerService.save(onr2);


        Visit visitCat=new Visit();
        visitCat.setDescription("Sneeze Cat");
        visitCat.setPet(tejalPetCat);
        visitCat.setVisitDate(LocalDate.now());
        visitService.save(visitCat);

        Speciality radiology=new Speciality();
        radiology.setDescription("Radiology");
        Speciality dentist=new Speciality();
        dentist.setDescription("Dentist");
        Speciality supergery=new Speciality();
        supergery.setDescription("Supergery");

        radiology=specialitiesService.save(radiology);
        dentist=specialitiesService.save(dentist);
        supergery=specialitiesService.save(supergery);

        Vet vet1=new Vet();
        vet1.setFirstName("Vet_1_FirstName");
        vet1.setLastName("Vet_1_LastName");
        vet1.getSpecialities().add(radiology);
        vet1.getSpecialities().add(dentist);

        vetService.save(vet1);

        Vet vet2=new Vet();
        vet2.setFirstName("Vet_2_FirstName");
        vet2.setLastName("Vet_2_LastName");
        vet2.getSpecialities().add(supergery);
        vet2.getSpecialities().add(radiology);

        vetService.save(vet2);

        System.out.println("Owner:"+ownerService.findAll().size());
        System.out.println("Vet:"+vetService.findAll().size());
        System.out.println("Pet:"+petTypeService.findAll().size());
        System.out.println("Speciality:"+specialitiesService.findAll().size());
        System.out.println("Visit:"+visitService.findAll().size());
    }
}
