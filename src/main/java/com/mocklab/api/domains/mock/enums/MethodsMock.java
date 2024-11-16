package com.mocklab.api.domains.mock.enums;

public enum MethodsMock {

    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE");

    private String text;

    MethodsMock(String text){
        this.text = text;
    }
}
