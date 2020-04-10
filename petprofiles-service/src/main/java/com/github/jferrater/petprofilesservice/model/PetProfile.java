package com.github.jferrater.petprofilesservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author joffryferrater
 */
@Data
public class PetProfile {

    @Schema(description = "The date created")
    private Date dateCreated;
    @Schema(description = "The creator of the pet profile. It can be the pet owner or the veterinarian", example = "john")
    private String createdBy;
    @Schema(description = "The date updated")
    private Date dateUpdated;
    @Schema(description = "Updated by can be a pet owner or the veterinarian updating the pet profile", example = "john")
    private String updatedBy;
    @Schema(description = "The name of the pet", example = "fluffy", required = true)
    @NotNull
    private String name;
    @Schema(description = "The owner of the pet", example = "john", required = true)
    @NotNull
    private String owner;
    @Schema(description = "The assigned veterinarian for the pet", example = "alice")
    private String veterinarian;
    @Schema(description = "The clinic location", example = "Stockholm")
    private String clinicLocation;
    @Schema(description = "The description of the pet", example = "Labrador, black")
    private String description;
}
