package com.mocklab.api.domains.mock.entities;

import com.mocklab.api.adapter.input.dto.RequestNewMockDTO;
import com.mocklab.api.domains.mock.enums.MethodsMock;
import com.mocklab.api.domains.mock.enums.StatusMock;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity(name = "mocks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idmock;
    private UUID idmockpublic;
    private UUID idproject;
    private UUID iduser;
    private String path;
    private String name;
    private String description;
    private String observation;
    @Enumerated(EnumType.STRING)
    private MethodsMock method;
    @Enumerated(EnumType.STRING)
    private StatusMock status;
    @Column(name = "status_code_response", length = 3)
    private String statusCodeResponse;
    @Column(name = "response_body")
    private String responseBody;
    @Column(name = "post_schema_request")
    private String postSchemaRequest;
    private ZonedDateTime createdat;
    private ZonedDateTime updatedat;
    private ZonedDateTime expiresin;

    @PrePersist
    void prePersist(){
        idmockpublic = UUID.randomUUID();
        createdat = ZonedDateTime.now();
        updatedat = createdat;

        status = StatusMock.ACTIVE;
    }

    @PreUpdate
    void preUpdate(){
        updatedat = ZonedDateTime.now();
    }

    public static Mock convert(RequestNewMockDTO newMock){
        Mock __newMock = new Mock();
        BeanUtils.copyProperties(newMock, __newMock);

        return __newMock;
    }


}
