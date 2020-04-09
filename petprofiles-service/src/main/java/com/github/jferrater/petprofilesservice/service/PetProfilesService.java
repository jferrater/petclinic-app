package com.github.jferrater.petprofilesservice.service;

import com.github.jferrater.petprofilesservice.exceptions.PetProfileNotFoundException;
import com.github.jferrater.petprofilesservice.repository.PetProfilesRepository;
import com.github.jferrater.petprofilesservice.repository.entity.PetProfileEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PetProfilesService {

    private PetProfilesRepository petProfilesRepository;

    public PetProfilesService(PetProfilesRepository petProfilesRepository) {
        this.petProfilesRepository = petProfilesRepository;
    }

    public PetProfileEntity createPetProfile(PetProfileEntity petProfileEntity) {
        return petProfilesRepository.save(petProfileEntity);
    }

    public PetProfileEntity getPetProfileByName(String name) {
        Optional<PetProfileEntity> byName = petProfilesRepository.findByName(name);
        if(byName.isPresent()) {
            return byName.get();
        } else {
            throw new PetProfileNotFoundException(String.format("The pet profile with name %s does not exist!", name));
        }
    }

    public boolean deletePetProfile(String name) {
        PetProfileEntity toBeDeleted = getPetProfileByName(name);
        petProfilesRepository.delete(toBeDeleted);
        return true;
    }

    public PetProfileEntity updatePetProfile(String name, PetProfileEntity petProfile) {
        getPetProfileByName(name);
        return petProfilesRepository.save(petProfile);
    }

    public List<PetProfileEntity> listPetProfiles() {
        List<PetProfileEntity> petProfiles = new ArrayList<>();
        Iterable<PetProfileEntity> all = petProfilesRepository.findAll();
        all.forEach(petProfiles::add);
        return petProfiles;
    }
}
