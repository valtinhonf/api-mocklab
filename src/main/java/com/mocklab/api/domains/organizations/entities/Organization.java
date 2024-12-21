package com.mocklab.api.domains.organizations.entities;

import com.mocklab.api.domains.organizations.enums.StatusOrganizationEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Data
@Entity(name = "organizations")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrganization;
    private UUID idPublic;
    private String fullname;
    private String shortname;
    private StatusOrganizationEnum status;
}
