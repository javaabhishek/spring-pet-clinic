package com.asoft.springpetclinic.services.springdatajpa;

import com.asoft.springpetclinic.model.Owner;
import com.asoft.springpetclinic.repositories.OwnerRepository;
import com.asoft.springpetclinic.repositories.PetRepository;
import com.asoft.springpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceUnitTest {

    public static final long OWNER_ID = 1l;
    public static final String OWNER_LAST_NAME = "Patil";
    @Mock
    OwnerRepository ownerRepository;
    @Mock
    PetRepository petRepository;
    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJpaService ownerSDJpaService;

    Owner ownerSample;
    @BeforeEach
    void setUp() {
        ownerSample=Owner.builder().id(OWNER_ID).lastName(OWNER_LAST_NAME).build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findById() {
        when(ownerRepository.findById(any())).thenReturn(Optional.of(ownerSample));
        Owner owner= ownerSDJpaService.findById(OWNER_ID);
        assertNotNull(owner);
        ArgumentCaptor<Long> captor=ArgumentCaptor.forClass(Long.class);
        verify(ownerRepository,times(1)).findById(captor.capture());
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(any())).thenReturn(Optional.empty());
        Owner owner= ownerSDJpaService.findById(OWNER_ID);
        assertNull(owner);
    }

    @Test
    void save() {
        ArgumentCaptor<Owner> captor=ArgumentCaptor.forClass(Owner.class);
        when(ownerRepository.save(captor.capture())).thenReturn(ownerSample);
        Owner savedOwner=ownerSDJpaService.save(ownerSample);
        assertNotNull(savedOwner);
        assertEquals(1l,savedOwner.getId());
        assertEquals(OWNER_LAST_NAME,savedOwner.getLastName());
        verify(ownerRepository).save(captor.capture());
    }

    @Test
    void findAll() {
        Set<Owner> mockData=new HashSet<>();
        mockData.add(ownerSample);
        mockData.add(Owner.builder().id(2l).lastName("Nals").build());
        when(ownerRepository.findAll()).thenReturn(mockData);
        assertEquals(2, ownerSDJpaService.findAll().size());
    }

    @Test
    void delete() {
        ownerSDJpaService.delete(ownerSample);
        verify(ownerRepository,times(1)).delete(any());
    }

    @Test
    void deleteById() {
        ownerSDJpaService.deleteById(ownerSample.getId());
        verify(ownerRepository,times(1)).deleteById(anyLong());
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(anyString())).thenReturn(Optional.of(ownerSample));
        Owner searchedOwner=ownerSDJpaService.findByLastName(OWNER_LAST_NAME);
        assertNotNull(searchedOwner);
        assertEquals(OWNER_LAST_NAME,searchedOwner.getLastName());
        verify(ownerRepository).findByLastName(anyString());
    }
}