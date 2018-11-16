package com.nastinio.spring.enums;

public enum CorrelationType {
    JOINTLY ("jointly"),
    EXCLUDE ("exclude"),
    NONE ("none");

    private final String value;

    CorrelationType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
