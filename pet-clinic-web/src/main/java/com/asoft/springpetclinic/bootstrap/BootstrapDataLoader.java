package com.asoft.springpetclinic.bootstrap;

import com.asoft.springpetclinic.model.*;
import com.asoft.springpetclinic.services.OwnerService;
import com.asoft.springpetclinic.services.PetTypeService;
import com.asoft.springpetclinic.services.SpecialityService;
import com.asoft.springpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class BootstrapDataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialitiesService;


    public BootstrapDataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialitiesService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialitiesService = specialitiesService;
    }

    @Override
    public void run(String... args) throws Exception {
        PetType dog=new PetType();
        dog.setName("Dog");
        PetType savedDogPetType=petTypeService.save(dog);

        PetType cat=new PetType();
        dog.setName("Cat");
        PetType savedCatPetType=petTypeService.save(cat);

        Owner onr1=new Owner();
        onr1.setFirstName("Abhishek");
        onr1.setLastName("Patil");
        onr1.setCity("Kolhapur");
        onr1.setAddress("Kolhapur");
        onr1.setTelephone("8657888773");

        Pet abhishekPetDog=new Pet();
        abhishekPetDog.setPetName("BhuBhu");
        abhishekPetDog.setPetType(savedDogPetType);
        abhishekPetDog.setBirthDate(LocalDate.now());
        onr1.getPets().add(abhishekPetDog);

        ownerService.save(onr1);

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

        Specialty radiology=new Specialty();
        radiology.setDescription("Radiology");
        Specialty dentist=new Specialty();
        dentist.setDescription("Dentist");
        Specialty supergery=new Specialty();
        supergery.setDescription("Supergery");

        radiology=specialitiesService.save(radiology);
        dentist=specialitiesService.save(dentist);
        supergery=specialitiesService.save(supergery);

        Vet vet1=new Vet();
        vet1.setFirstName("Vet_1_FirstName");
        vet1.setLastName("Vet_1_LastName");
        vet1.getSpecialties().add(radiology);
        vet1.getSpecialties().add(dentist);

        vetService.save(vet1);

        Vet vet2=new Vet();
        vet2.setFirstName("Vet_2_FirstName");
        vet2.setLastName("Vet_2_LastName");
        vet2.getSpecialties().add(supergery);
        vet2.getSpecialties().add(radiology);

        vetService.save(vet2);

        System.out.println(ownerService.findAll().size());
        System.out.println(vetService.findAll().size());
        System.out.println(petTypeService.findAll().size());
     }
}
