package com.github.jferrater.petprofilesservice.repository.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "pet_profiles")
@EntityListeners(AuditingEntityListener.class)
public class PetProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private Date dateCreated;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private Date dateUpdated;

    @LastModifiedBy
    private String updatedBy;

    private String name;

    private String owner;

    private String veterinarian;

    private String clinicLocation;

    private String description;
}
