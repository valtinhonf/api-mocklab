package com.mocklab.api.domains.mock.enums;

public enum MethodsMock {

    GET("GET", "For GET Methods"),
    POST("POST", "For POST Methods"),
    PUT("PUT", "For PUT Methods"),
    DELETE("DELETE", "For DELETE Methods");

    private String text;
    private String description;

    MethodsMock(String text, String description){
        this.text = text;
        this.description = description;
    }
}
