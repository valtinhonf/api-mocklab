package com.mocklab.api.adapter.input.dto;

import com.mocklab.api.domains.mock.entities.Mock;
import com.mocklab.api.domains.mock.enums.StatusMock;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMockDTO {

    private UUID idmockpublic;
    private String path;
    private String method;
    private String statusCodeResponse;
    private String responseBody;
    private String name;
    private String observation;
    private String description;
    private StatusMock status;
    private ZonedDateTime createdat;
    private ZonedDateTime updatedat;
    private ZonedDateTime expiresin;

    public static ResponseMockDTO convert(Mock mock){
        ResponseMockDTO dto = new ResponseMockDTO();
        BeanUtils.copyProperties(mock, dto);

        return dto;
    }

    public static List<ResponseMockDTO> convert(List<Mock> mocks){
        List<ResponseMockDTO> dtos = new ArrayList<>();
        mocks.forEach(mock -> {
            ResponseMockDTO dto = new ResponseMockDTO();
            BeanUtils.copyProperties(mock, dto);
            dtos.add(dto);
        });
        return dtos;
    }


}
