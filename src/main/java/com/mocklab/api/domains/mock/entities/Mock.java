package com.mocklab.api.domains.mock.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
public class Mock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID idmock;
    private UUID idproject;
    private String name;
    private String description;
    private String observation;
    private String path;
    private String method;
    private String status;
    private Integer statusCodeResponse;
    private String responseBody;
    private ZonedDateTime createdat;
    private ZonedDateTime updatedat;
    private ZonedDateTime expiresAt;


}
