package com.asoft.springpetclinic.controller;

import com.asoft.springpetclinic.model.Owner;
import com.asoft.springpetclinic.model.Pet;
import com.asoft.springpetclinic.model.PetType;
import com.asoft.springpetclinic.services.OwnerService;
import com.asoft.springpetclinic.services.PetService;
import com.asoft.springpetclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)//important: if you don't do this then @Mock annotated bean will not create
class PetControllerUnitTest {

    @Mock
    OwnerService ownerService;
    @Mock
    PetService petService;
    @Mock
    PetTypeService petTypeService;

    @InjectMocks
    PetController petController;
    MockMvc mockMvc;
    Owner owner;
    Pet pet;
    PetType petType;
    @BeforeEach
    void setUp() {
        petType=PetType.builder().name("Cat").build();
        pet=Pet.builder().id(1l).petName("Bablu").petType(petType).build();
        owner=Owner.builder().id(1l).firstName("abhishek").build();
        owner.getPets().add(pet);
        mockMvc=MockMvcBuilders.standaloneSetup(petController).build();
    }

    @Test
    void populatePetTypes() throws Exception {
        /*when(petTypeService.findAll()).thenReturn(Set.of(petType));
        mockMvc.perform(MockMvcRequestBuilders.get("/owners/1"))
               .andExpect(status().isOk());*/
    }

    @Test
    void initCreationForm() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(owner);
        mockMvc.perform(MockMvcRequestBuilders.get("/owners/{ownerId}/pets/new",owner.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("pets/createOrUpdatePetForm"))
                .andExpect(model().attributeExists("pet"));
    }

    @Test
    void processCreationForm_success() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(owner);

        mockMvc.perform(MockMvcRequestBuilders.post("/owners/{ownerId}/pets/new",owner.getId()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/{ownerId}"));
        verify(ownerService,times(1)).save(any());
    }

    /*@Test
    void processCreationForm_fail() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(owner);

        mockMvc.perform(MockMvcRequestBuilders.post("/owners/{ownerId}/pets/new",owner.getId()))
                .andExpect(model().attributeHasErrors("pet"))
                .andExpect(status().isOk())
                .andExpect(view().name("pets/createOrUpdatePetForm"));
    }*/

    @Test
    void initUpdateForm() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(owner);
        mockMvc.perform(MockMvcRequestBuilders.get("/owners/1/pets/1/edit"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("pet"))
                .andExpect(view().name("pets/createOrUpdatePetForm"));
    }

    @Test
    void processUpdateForm() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(owner);
        mockMvc.perform(MockMvcRequestBuilders.post("/owners/1/pets/1/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/{ownerId}"));
        verify(ownerService,times(1)).save(any());
    }
}