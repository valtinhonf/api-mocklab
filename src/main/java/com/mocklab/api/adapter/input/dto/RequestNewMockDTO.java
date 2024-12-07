package com.mocklab.api.adapter.input.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class RequestNewMockDTO {

    private String idproject;
    private UUID iduser;
    @NotNull(message = "Path can't be null")
    private String path;
    @NotNull(message = "Method can't be null")
    private String method;
    private String statusCodeResponse;
    private String responseBody;
    private String name;
    private String observation;
    private String description;

}
