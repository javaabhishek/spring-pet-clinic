package com.asoft.springpetclinic.controller;

import com.asoft.springpetclinic.model.Owner;
import com.asoft.springpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.util.CollectionUtils;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerUnitTest {

    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController ownerController;

    Set<Owner> ownerSet;

    MockMvc mockMvc;
    @BeforeEach
    void setUp() {
        ownerSet=new HashSet<>();
        ownerSet.add(Owner.builder().id(1l).firstName("abhishek").lastName("patil").build());
        ownerSet.add(Owner.builder().id(2l).firstName("Tejal").lastName("patil").build());
        mockMvc= MockMvcBuilders.standaloneSetup(ownerController).build();
    }

    /*@Test
    void index() throws Exception {
        when(ownerService.findAll()).thenReturn(ownerSet);
        mockMvc.perform(MockMvcRequestBuilders.get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners",hasSize(2)));
    }*/
  /*  @Test
    void indexSecondPath() throws Exception {
        when(ownerService.findAll()).thenReturn(ownerSet);
        mockMvc.perform(MockMvcRequestBuilders.get("/owners/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"));
    }
    @Test
    void indexThirdPath() throws Exception {
        when(ownerService.findAll()).thenReturn(ownerSet);
        mockMvc.perform(MockMvcRequestBuilders.get("/owners/index.html"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"));
    }*/

    @Test
    void findOwners() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/findOwners"))
                .andExpect(model().attributeExists("owner"));
        verifyNoInteractions(ownerService);//because we have not implemented logic in controller class
        //so making sure out service class is not executed.
    }

    @Test
    void processFindOwnerReturnOne() throws Exception {
        when(ownerService.findAllOwnersByLastNameLike(anyString()))
                .thenReturn(CollectionUtils.toSet(new Owner[]{Owner.builder().lastName("Test").id(1l).build()}));
        mockMvc.perform(MockMvcRequestBuilders.get("/owners"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));
    }

    @Test
    void processFindOwnerReturnMany() throws Exception {
        when(ownerService.findAllOwnersByLastNameLike(anyString())).thenReturn(ownerSet);
        mockMvc.perform(MockMvcRequestBuilders.get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("/owners/ownersList"))
                .andExpect(model().attribute("listOwners",hasSize(2)));
    }

    @Test
    void processFindOwnerReturnAllWithEmptyParam() throws Exception {
        when(ownerService.findAllOwnersByLastNameLike("%"+""+"%")).thenReturn(ownerSet);
        mockMvc.perform(MockMvcRequestBuilders.get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("/owners/ownersList"))
                .andExpect(model().attribute("listOwners",hasSize(2)));
    }
    @Test
    void showOwner() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().firstName("Tejal").lastName("patil").id(1l).build());
        Owner owner= ownerService.findById(1l);
        assertNotNull(owner);
        mockMvc.perform(MockMvcRequestBuilders.get("/owners/1"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("owner",hasProperty("firstName",is("Tejal"))))
                .andExpect(model().attribute("owner",hasProperty("lastName",is("patil"))))
                .andExpect(view().name("owners/ownerDetails"));
    }
}