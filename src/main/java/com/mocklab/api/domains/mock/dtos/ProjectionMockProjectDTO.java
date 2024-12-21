package com.mocklab.api.domains.mock.dtos;

import java.util.UUID;

public interface ProjectionMockProjectDTO {
    UUID getIdmockpublic();
    UUID getIdproject();
    UUID getIduser();
    String getPath();
    String getName();
    String getDescription();
    String getObservation();
    String getMethod();
    String getStatus();
    Integer getProjectIdSequence();
    String getProjectName();
    String getProjectDescription();
    String getProjectObservation();
}
