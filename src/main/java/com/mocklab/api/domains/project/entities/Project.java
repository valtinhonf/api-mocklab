package com.mocklab.api.domains.project.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID idproject;
    private String name;
    private String description;
    private String observation;
    private ZonedDateTime createdat;
    private ZonedDateTime updatedat;
}
