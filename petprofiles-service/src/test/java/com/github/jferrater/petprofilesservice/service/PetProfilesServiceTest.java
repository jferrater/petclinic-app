package com.github.jferrater.petprofilesservice.service;

import com.github.jferrater.petprofilesservice.exceptions.PetProfileNotFoundException;
import com.github.jferrater.petprofilesservice.repository.PetProfilesRepository;
import com.github.jferrater.petprofilesservice.repository.entity.PetProfileEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("test")
@DataJpaTest
class PetProfilesServiceTest {

    private PetProfilesService target;

    @Autowired
    private PetProfilesRepository petProfilesRepository;

    @BeforeEach
    void setUp() {
        target = new PetProfilesService(petProfilesRepository);
        initData();
    }

    @AfterEach
    void cleanUp() {
        petProfilesRepository.deleteAll();
    }

    void initData() {
        PetProfileEntity fluffy = testEntity("ussop", "dodong", "alice", "labrador, white");
        petProfilesRepository.save(fluffy);
        PetProfileEntity samsam = testEntity("roger", "gerry", "wella", "terrier, white");
        petProfilesRepository.save(samsam);
    }

    private PetProfileEntity testEntity(String name, String owner, String veterinarian, String description) {
        PetProfileEntity fluffy = new PetProfileEntity();
        fluffy.setName(name);
        fluffy.setOwner(owner);
        fluffy.setVeterinarian(veterinarian);
        fluffy.setDescription(description);
        return fluffy;
    }

    @Test
    void shouldCreatePetProfile() {
        PetProfileEntity robin = testEntity("robin", "cecilia", "alice", "siberian husky, white");

        PetProfileEntity result = target.createPetProfile(robin);

        assertThat(result.getName(), is("robin"));
        assertThat(result.getVeterinarian(), is("alice"));
        assertThat(result.getCreatedBy(), is(notNullValue()));
    }

    @Test
    void shouldGetPetProfileByName() {
        PetProfileEntity result = target.getPetProfileByName("ussop");

        assertThat(result, is(notNullValue()));
        assertThat(result.getOwner(), is("dodong"));
        assertThat(result.getVeterinarian(), is("alice"));
        assertThat(result.getDescription(), is("labrador, white"));
    }

    @Test
    void shouldThrowPetProfileNotFoundExceptionWhenGettingNonExistingPetProfile() {
        assertThrows(PetProfileNotFoundException.class, () -> target.getPetProfileByName("does_not_exist"));
    }

    @Test
    void shouldDeleteAPetProfile() {
        boolean result = target.deletePetProfile("roger");
        assertThat(result, is(true));
    }

    @Test
    void shouldThrowPetProfileNotFoundExceptionWhenDeletingNonExistingPetProfile() {
        assertThrows(PetProfileNotFoundException.class, () -> target.deletePetProfile("does_not_exist"));
    }

    @Test
    void shouldUpdatePetProfile() {
        PetProfileEntity petProfile = testEntity("ussop", "dedeth", "alice", "labrador, black and white");

        PetProfileEntity result = target.updatePetProfile("ussop", petProfile);

        assertThat(result, is(notNullValue()));
        assertThat(result.getName(), is("ussop"));
        assertThat(result.getOwner(), is("dedeth"));
        assertThat(result.getVeterinarian(), is("alice"));
        assertThat(result.getDescription(), is("labrador, black and white"));
        assertThat(result.getDateUpdated(), is(notNullValue()));
    }

    @Test
    void shouldListPetProfiles() {
        List<PetProfileEntity> result = target.listPetProfiles();

        assertThat(result.size(), is(not(0)));
    }
}