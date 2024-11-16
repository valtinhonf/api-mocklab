package com.mocklab.api.domains.mock.enums;

import java.awt.*;

public enum StatusMock {

    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE");

    private String text;

    StatusMock(String text){
        this.text = text;
    }

}
