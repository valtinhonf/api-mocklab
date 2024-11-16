package com.mocklab.api.adapter.input.controllers;

import feign.Headers;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/mock")
public class MockController {

    @RequestMapping(value = "/{userId}/**")
    public ResponseEntity<?> handleDynamicRequests(
            @PathVariable("userId") String mockId,
            @RequestHeader Map<String, String> headers,
            @RequestParam Map<String, String> queryParams
    ){

        System.out.println(headers);
        System.out.println("chegou");

        HttpHeaders _headers = new HttpHeaders();
        _headers.add("Content-Type", "application/json");

        return ResponseEntity.status(300)
                .headers(_headers)
                .body("""
                {
                "NOME":VALTER,
                "ID DO USUARIO": "%s",
                "Parametros da Query": %s
                }
                """.formatted(Integer.parseInt(mockId), queryParams.toString()));
    }


}
