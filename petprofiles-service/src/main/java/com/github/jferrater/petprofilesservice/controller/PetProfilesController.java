package com.github.jferrater.petprofilesservice.controller;

import com.github.jferrater.petprofilesservice.model.PetProfile;
import com.github.jferrater.petprofilesservice.repository.entity.PetProfileEntity;
import com.github.jferrater.petprofilesservice.service.PetProfilesService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PetProfilesController {

    private PetProfilesService petProfilesService;
    private ModelMapper modelMapper;

    public PetProfilesController(PetProfilesService petProfilesService, ModelMapper modelMapper) {
        this.petProfilesService = petProfilesService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/petprofiles/{name}")
    public ResponseEntity<PetProfile> getPetProfileByName(@PathVariable("name") String name) {
        PetProfileEntity petProfileEntity = petProfilesService.getPetProfileByName(name);
        PetProfile petProfile = convertToDto(petProfileEntity);
        return new ResponseEntity<>(petProfile, HttpStatus.OK);
    }

    @PutMapping("/petprofiles/{name}")
    public ResponseEntity<PetProfile> updatePetProfile(@PathVariable("name") String name, @RequestBody PetProfile petProfile) {
        PetProfileEntity petProfileEntity = convertToEntity(petProfile);
        PetProfileEntity updatedPetProfileEntity = petProfilesService.updatePetProfile(name, petProfileEntity);
        PetProfile updatedPetProfile = convertToDto(updatedPetProfileEntity);
        return new ResponseEntity<>(updatedPetProfile, HttpStatus.OK);
    }

    private PetProfileEntity convertToEntity(PetProfile petProfile) {
        return modelMapper.map(petProfile, PetProfileEntity.class);
    }

    private PetProfile convertToDto(PetProfileEntity petProfileEntity) {
        return modelMapper.map(petProfileEntity, PetProfile.class);
    }
}
