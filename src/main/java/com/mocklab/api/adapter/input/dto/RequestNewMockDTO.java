package com.mocklab.api.adapter.input.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.mocklab.api.domains.mock.enums.MethodsMock;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class RequestNewMockDTO {

    @JsonAlias("idProjectSelected")
    private UUID idproject;
    private UUID iduser;
    @NotNull(message = "Path can't be null")
    private String path;
    @NotNull(message = "Method can't be null")
    private MethodsMock method;
    private String statusCodeResponse;
    private String responseBody;
    private String postSchemaRequest;
    private String name;
    private String observation;
    private String description;

}
