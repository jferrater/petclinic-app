package com.github.jferrater.petprofilesservice.controller;

import com.github.jferrater.petprofilesservice.repository.entity.PetProfileEntity;
import com.github.jferrater.petprofilesservice.service.PetProfilesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PetProfilesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PetProfilesService petProfilesService;

    @Test
    void shouldGetPetProfilesByName() throws Exception {
        PetProfileEntity entity = testEntity("fluffy", "dodong", "alice", "black white");
        entity.setDateCreated(new Date());
        entity.setCreatedBy("alice");
        when(petProfilesService.getPetProfileByName("fluffy")).thenReturn(entity);

        mockMvc.perform(get("/petprofiles/fluffy")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("fluffy")))
                .andExpect(jsonPath("$.owner", is("dodong")))
                .andExpect(jsonPath("$.veterinarian", is("alice")))
                .andExpect(jsonPath("$.description", is("black white")));
    }

    private PetProfileEntity testEntity(String name, String owner, String veterinarian, String description) {
        PetProfileEntity fluffy = new PetProfileEntity();
        fluffy.setName(name);
        fluffy.setOwner(owner);
        fluffy.setVeterinarian(veterinarian);
        fluffy.setDescription(description);
        return fluffy;
    }
}