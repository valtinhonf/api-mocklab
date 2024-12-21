package com.mocklab.api.domains.organizations.enums;

public enum StatusOrganizationEnum {

    AWAITING("AWAITING"),
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"),
    BLOCKED("BLOCKED");

    private String text;

    StatusOrganizationEnum(String text) {
        this.text = text;
    }

}
