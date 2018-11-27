package com.nastinio.spring.enums;

public enum ExtraOptionStatus {
    CONNECTED("connected"),
    IN_BASKET_ON("basketOn"),
    IN_BASKET_OFF("basketOff"),
    NONE("none");

    private final String value;

    ExtraOptionStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
