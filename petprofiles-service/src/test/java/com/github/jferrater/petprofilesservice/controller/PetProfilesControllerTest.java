package com.github.jferrater.petprofilesservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jferrater.petprofilesservice.exceptions.PetProfileNotFoundException;
import com.github.jferrater.petprofilesservice.model.PetProfile;
import com.github.jferrater.petprofilesservice.repository.entity.PetProfileEntity;
import com.github.jferrater.petprofilesservice.service.PetProfilesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author joffryferrater
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
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

    @Test
    void shouldReturnNotFound() throws Exception {
        when(petProfilesService.getPetProfileByName("does-not-exist")).thenThrow(new PetProfileNotFoundException("The pet profile with name does-not-exist does not exist!"));

        mockMvc.perform(get("/petprofiles/does-not-exist")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code", is(404)))
                .andExpect(jsonPath("$.status", is("Not Found")))
                .andExpect(jsonPath("$.message", is("The pet profile with name does-not-exist does not exist!")));
    }

    @Test
    void shouldCreatePetProfile() throws Exception {
        PetProfileEntity entity = testEntity("fluffy", "dodong", "alice", "black white");
        String petProfile = testDto("fluffy", "dodong", "alice", "black white");
        when(petProfilesService.createPetProfile(any(PetProfileEntity.class))).thenReturn(entity);

        mockMvc.perform(post("/petprofiles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(petProfile))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("fluffy")))
                .andExpect(jsonPath("$.owner", is("dodong")))
                .andExpect(jsonPath("$.veterinarian", is("alice")))
                .andExpect(jsonPath("$.description", is("black white")));
    }

    @Test
    void shouldUpdateAPetProfile() throws Exception {
        PetProfileEntity entity = testEntity("fluffy", "dodong", "alice", "black white");
        String petProfile = testDto("fluffy", "dedeth", "bob", "black white");
        when(petProfilesService.updatePetProfile(anyString(), any(PetProfileEntity.class))).thenReturn(entity);

        mockMvc.perform(put("/petprofiles/fluffy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(petProfile))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("fluffy")))
                .andExpect(jsonPath("$.owner", is("dodong")))
                .andExpect(jsonPath("$.veterinarian", is("alice")))
                .andExpect(jsonPath("$.description", is("black white")));
    }

    @Test
    void shouldDeletePetProfilesByName() throws Exception {
        when(petProfilesService.deletePetProfile("fluffy")).thenReturn(true);

        mockMvc.perform(delete("/petprofiles/fluffy")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldListPetProfiles() throws Exception {
        PetProfileEntity entity = testEntity("fluffy", "dodong", "alice", "black white");
        when(petProfilesService.listPetProfiles()).thenReturn(Collections.singletonList(entity));

        mockMvc.perform(get("/petprofiles")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("fluffy")))
                .andExpect(jsonPath("$[0].owner", is("dodong")))
                .andExpect(jsonPath("$[0].veterinarian", is("alice")))
                .andExpect(jsonPath("$[0].description", is("black white")));
    }

    private PetProfileEntity testEntity(String name, String owner, String veterinarian, String description) {
        PetProfileEntity petProfileEntity = new PetProfileEntity();
        petProfileEntity.setName(name);
        petProfileEntity.setOwner(owner);
        petProfileEntity.setVeterinarian(veterinarian);
        petProfileEntity.setDescription(description);
        return petProfileEntity;
    }

    private String testDto(String name, String owner, String veterinarian, String description) throws JsonProcessingException {
        PetProfile petProfile = new PetProfile();
        petProfile.setName(name);
        petProfile.setOwner(owner);
        petProfile.setVeterinarian(veterinarian);
        petProfile.setDescription(description);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(petProfile);
    }
}