package com.github.jferrater.petprofilesservice.controller;

import com.github.jferrater.petprofilesservice.model.ApiError;
import com.github.jferrater.petprofilesservice.model.PetProfile;
import com.github.jferrater.petprofilesservice.repository.entity.PetProfileEntity;
import com.github.jferrater.petprofilesservice.service.PetProfilesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author joffryferrater
 */
@RestController
@Tag(name = "petprofiles_service", description = "The Pet Profile API")
@CrossOrigin(maxAge = 3600)
public class PetProfilesController {

    private PetProfilesService petProfilesService;
    private ModelMapper modelMapper;

    public PetProfilesController(PetProfilesService petProfilesService, ModelMapper modelMapper) {
        this.petProfilesService = petProfilesService;
        this.modelMapper = modelMapper;
    }

    @Operation(
            summary = "Get the pet profile by name",
            description = "Returns the pet profile information",
            tags = "petprofiles_service"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Ok", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PetProfile.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiError.class))),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiError.class))),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiError.class)))
            }
    )
    @GetMapping("/petprofiles/{name}")
    public ResponseEntity<PetProfile> getPetProfileByName(@Parameter(description = "The name of the pet.") @PathVariable("name") String name) {
        PetProfileEntity petProfileEntity = petProfilesService.getPetProfileByName(name);
        PetProfile petProfile = convertToDto(petProfileEntity);
        return new ResponseEntity<>(petProfile, HttpStatus.OK);
    }

    @Operation(
            summary = "Create new pet profile",
            description = "Returns the pet profile information",
            tags = "petprofiles_service"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Ok", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PetProfile.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiError.class))),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiError.class)))
            }
    )
    @PostMapping("/petprofiles")
    public ResponseEntity<PetProfile> createPetProfile(@RequestBody PetProfile petProfile) {
        PetProfileEntity petProfileEntity = convertToEntity(petProfile);
        PetProfileEntity createdPetProfileEntity = petProfilesService.createPetProfile(petProfileEntity);
        PetProfile createdPetProfile = convertToDto(createdPetProfileEntity);
        return new ResponseEntity<>(createdPetProfile, HttpStatus.OK);
    }

    @Operation(
            summary = "Update the pet profile",
            description = "Returns the pet profile information",
            tags = "petprofiles_service"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Ok", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PetProfile.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiError.class))),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiError.class))),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiError.class)))
            }
    )
    @PutMapping("/petprofiles/{name}")
    public ResponseEntity<PetProfile> updatePetProfile(@Parameter(description = "The name of the pet.") @PathVariable("name") String name, @RequestBody PetProfile petProfile) {
        PetProfileEntity petProfileEntity = convertToEntity(petProfile);
        PetProfileEntity updatedPetProfileEntity = petProfilesService.updatePetProfile(name, petProfileEntity);
        PetProfile updatedPetProfile = convertToDto(updatedPetProfileEntity);
        return new ResponseEntity<>(updatedPetProfile, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete a pet profile",
            description = "Delete a pet profile API",
            tags = "petprofiles_service"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Ok", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PetProfile.class))),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiError.class))),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiError.class)))
            }
    )
    @DeleteMapping("/petprofiles/{name}")
    public ResponseEntity<Void> deletePetProfileByName(@Parameter(description = "The name of the pet.") @PathVariable("name") String name) {
        petProfilesService.deletePetProfile(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(
            summary = "List all pet profiles",
            description = "Returns the list of pet profiles",
            tags = "petprofiles_service"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Ok", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = PetProfile.class)))),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiError.class)))
            }
    )
    @GetMapping("/petprofiles")
    public ResponseEntity<List<PetProfile>> getPetProfileByName() {
        List<PetProfileEntity> petProfileEntities = petProfilesService.listPetProfiles();
        List<PetProfile> petProfiles = petProfileEntities.stream().map(this::convertToDto).collect(toList());
        return new ResponseEntity<>(petProfiles, HttpStatus.OK);
    }

    private PetProfileEntity convertToEntity(PetProfile petProfile) {
        return modelMapper.map(petProfile, PetProfileEntity.class);
    }

    private PetProfile convertToDto(PetProfileEntity petProfileEntity) {
        return modelMapper.map(petProfileEntity, PetProfile.class);
    }
}
