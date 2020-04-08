package com.github.jferrater.petprofilesservice.repository;

import com.github.jferrater.petprofilesservice.repository.entity.PetProfileEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PetProfilesRepository extends CrudRepository<PetProfileEntity, Long> {

    Optional<PetProfileEntity> findByName(String name);
}
