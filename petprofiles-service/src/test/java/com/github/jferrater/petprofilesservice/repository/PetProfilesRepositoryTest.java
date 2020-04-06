package com.github.jferrater.petprofilesservice.repository;

import com.github.jferrater.petprofilesservice.repository.entity.PetProfileEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
class PetProfilesRepositoryTest {

    @Autowired
    private PetProfilesRepository target;

    @BeforeEach
    void setUp() {
        PetProfileEntity petProfileEntity = new PetProfileEntity();
        petProfileEntity.setName("kiat");
        petProfileEntity.setOwner("badlungon");
        petProfileEntity.setClinicLocation("Lacion");
        petProfileEntity.setVeterinarian("Tulin");
        petProfileEntity.setDescription("gwapo na askal");

        PetProfileEntity result = target.save(petProfileEntity);
        assertThat(result, is(notNullValue()));
    }

    @Test
    void shouldGetProfileEntity() {
        Optional<PetProfileEntity> petProfileEntityOptional = target.findByName("kiat");

        assertThat(petProfileEntityOptional.isPresent(), is(true));

        PetProfileEntity result = petProfileEntityOptional.get();

        assertThat(result.getName(), is("kiat"));
        assertThat(result.getOwner(), is("badlungon"));
        assertThat(result.getClinicLocation(), is("Lacion"));
        assertThat(result.getVeterinarian(), is("Tulin"));
        assertThat(result.getDescription(), is("gwapo na askal"));    }
}