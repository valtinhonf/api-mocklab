package com.mocklab.api.domains.project.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity(name = "projects")
@Data
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idproject;
    @Column(name = "idorganization")
    private UUID idOrganization;
    @Column(name = "idsequence")
    private Integer idSequence;
    @Column(length = 50)
    private String name;
    @Column(length = 150)
    private String description;
    @Column(length = 150)
    private String observation;
    private ZonedDateTime createdat;
    private ZonedDateTime updatedat;

    @PrePersist
    void prePersist(){
 //       idproject = UUID.randomUUID();
        createdat = ZonedDateTime.now();
        updatedat = createdat;
    }
}
