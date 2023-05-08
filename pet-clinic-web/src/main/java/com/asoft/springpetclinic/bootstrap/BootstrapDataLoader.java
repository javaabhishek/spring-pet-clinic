package com.asoft.springpetclinic.bootstrap;

import com.asoft.springpetclinic.model.Owner;
import com.asoft.springpetclinic.model.Vet;
import com.asoft.springpetclinic.services.OwnerService;
import com.asoft.springpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapDataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public BootstrapDataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {
        Owner onr1=new Owner();
        onr1.setFirstName("Abhishek");
        onr1.setId(1l);
        onr1.setLastName("Patil");

        ownerService.save(onr1);
        Owner onr2=new Owner();
        onr2.setFirstName("Tejal");
        onr2.setId(2l);
        onr2.setLastName("Patil");
        ownerService.save(onr2);

        Vet vet1=new Vet();
        vet1.setId(1l);
        vet1.setFirstName("Vet_1_FirstName");
        vet1.setLastName("Vet_1_LastName");

        vetService.save(vet1);
        Vet vet2=new Vet();
        vet2.setId(2l);
        vet2.setFirstName("Vet_2_FirstName");
        vet2.setLastName("Vet_2_LastName");
        vetService.save(vet2);

        System.out.println(ownerService.findAll().size());
        System.out.println(vetService.findAll().size());
     }
}
