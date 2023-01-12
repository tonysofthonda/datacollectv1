package com.honda.hdm.datacollect.api.helpers;

public enum FetchType {
    LAZY("lazy"),
    EAGER("eager");

    private final String value;

    FetchType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
