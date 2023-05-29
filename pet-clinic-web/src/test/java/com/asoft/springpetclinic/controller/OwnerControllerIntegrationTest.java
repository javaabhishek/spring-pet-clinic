package com.asoft.springpetclinic.controller;

import com.asoft.springpetclinic.model.Owner;
import com.asoft.springpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class OwnerControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private OwnerService ownerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void processCreationForm_withValidOwner() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/owners/new")
                .param("firstName", "John")
                .param("lastName", "Doe")
                .param("address", "123 Main St")
                .param("city", "City")
                .param("telephone", "1234567890"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrlPattern("/owners/*"));
        
        // Add assertions here to verify the behavior
    }

    @Test
    void processCreationForm_withInvalidOwner() throws Exception {
       /* mockMvc.perform(MockMvcRequestBuilders.post("/owners/new")
                .param("firstName", "")
                .param("lastName", "")
                .param("address", "")
                .param("city", "")
                .param("telephone", ""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("owners/createOrUpdateOwnerForm"));*/
        
        // Add assertions here to verify the behavior
    }
}
