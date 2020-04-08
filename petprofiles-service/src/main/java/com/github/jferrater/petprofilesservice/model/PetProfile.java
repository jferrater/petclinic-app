package com.github.jferrater.petprofilesservice.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class PetProfile {


    private Date dateCreated;
    private String createdBy;
    private Date dateUpdated;
    private String updatedBy;
    @NotNull
    private String name;
    @NotNull
    private String owner;
    private String veterinarian;
    private String clinicLocation;
    private String description;
}
