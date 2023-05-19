package com.asoft.springpetclinic.services.map;

import com.asoft.springpetclinic.model.Owner;
import com.asoft.springpetclinic.services.PetService;
import com.asoft.springpetclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapUnitTest {

    OwnerServiceMap ownerServiceMap;
    Long ownerId=1l;

    @BeforeEach
    void setUp() {
        ownerServiceMap=new OwnerServiceMap(new PetTypeServiceMap(),new PetServiceMap());
        ownerServiceMap.save(Owner.builder().id(ownerId)
                .lastName("patil").build());
    }

    @Test
    void findById() {
       Owner owner= ownerServiceMap.findById(ownerId);
       assertNotNull(owner);
    }

    @Test
    void save() {
        Owner toSave=Owner.builder().id(2l).build();
        Owner savedOwner=ownerServiceMap.save(toSave);
        assertNotNull(savedOwner);
    }

    @Test
    void findAll() {
        assertEquals(1l, ownerServiceMap.findAll().size());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(ownerId);
        assertEquals(0,ownerServiceMap.findAll().size());
    }

    @Test
    void delete() {
        Owner toDelete=ownerServiceMap.findById(ownerId);
        ownerServiceMap.delete(toDelete);
        assertEquals(0,ownerServiceMap.findAll().size());
    }

    @Test
    void findByLastName() {
        Owner findOnr=ownerServiceMap.findByLastName("patil");
        assertNotNull(findOnr);
    }
}