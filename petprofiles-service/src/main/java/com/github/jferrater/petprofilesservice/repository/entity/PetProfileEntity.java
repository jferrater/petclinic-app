package com.github.jferrater.petprofilesservice.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @author joffryferrater
 */
@Data
@Entity
@Table(name = "pet_profiles")
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class PetProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(name = "date_created")
    private Date dateCreated;

    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;

    @LastModifiedDate
    @Column(name = "date_updated")
    private Date dateUpdated;

    @LastModifiedBy
    @Column(name = "updated_by")
    private String updatedBy;

    private String name;

    private String owner;

    private String veterinarian;

    @Column(name = "clinic_location")
    private String clinicLocation;

    private String description;
}
