package com.mocklab.api.adapter.input.controllers;

import com.mocklab.api.adapter.input.dto.RequestNewMockDTO;
import com.mocklab.api.adapter.input.dto.ResponseMockDTO;
import com.mocklab.api.domains.mock.dtos.ProjectionMockProjectDTO;
import com.mocklab.api.domains.mock.entities.Mock;
import com.mocklab.api.domains.mock.services.MockFindService;
import com.mocklab.api.domains.mock.services.MockServices;
import feign.Headers;
import feign.Request;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mock")
@CrossOrigin("*")
public class MockController {

    private MockFindService mockFindService;
    private MockServices mockSrv;

    public MockController(MockFindService mockFindSrv, MockServices mockSrv){
        this.mockFindService = mockFindSrv;
        this.mockSrv = mockSrv;
    }

    @PostMapping(value = "", produces = "application/json")
    public ResponseEntity<ResponseMockDTO> saveNew(@RequestBody RequestNewMockDTO newMock) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(mockSrv.saveNew(newMock));
    }

    @PutMapping(value = "", produces = "application/json")
    public ResponseEntity<ResponseMockDTO> updateMock(@RequestBody Mock mock) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(mockSrv.update(mock));
    }

    @GetMapping(value = "/byuser/{userId}", produces = "application/json")
    public ResponseEntity<List<ResponseMockDTO>> listMockFromUser(@PathVariable("userId") String idUser){
        return ResponseEntity.status(HttpStatus.OK).body(mockSrv.findAllByUser(idUser));
    }

    @GetMapping(value = "/byorganization/{organizationId}")
    public ResponseEntity<List<ProjectionMockProjectDTO>> listMocksFromOrganization(@PathVariable("organizationId") String organizationId){
        return ResponseEntity.status(HttpStatus.OK).body(mockSrv.findAllByOrganization(organizationId));
    }

    @GetMapping(value = "/{idMock}", produces = "application/json")
    public ResponseEntity<ResponseMockDTO> getMock(@PathVariable("idMock") String idMock){
        return ResponseEntity.status(HttpStatus.OK).body(mockSrv.findById(idMock));
    }

    @DeleteMapping(value = "/{idUser}/{idPublicMock}", produces = "application/json")
    public ResponseEntity<ResponseMockDTO> getMock(@PathVariable("idUser") String idUser, @PathVariable("idPublicMock") String idPublicMock){
        mockSrv.deleteMock(idUser, idPublicMock);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(value = "/{userId}/**")
    public ResponseEntity<?> handleDynamicRequests(
            HttpServletRequest request,
            @PathVariable("userId") String mockId,
            @RequestHeader Map<String, String> headers,
            @RequestParam Map<String, String> queryParams
    ) throws Exception{

        String qry = request.getQueryString();
        HttpHeaders _headers = new HttpHeaders();
        Mock mock = this.mockFindService.findMock(request.getRequestURI(), request.getMethod(), mockId);

        return ResponseEntity.status(Integer.parseInt(mock.getStatusCodeResponse()))
                .headers(_headers)
                .body(mock.getResponseBody());
    }


}
