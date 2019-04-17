package com.guess.util.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum HttpHeader {

    X_TOTAL_COUNT("X-Total-Count");

    private String value;

    HttpHeader(String value) {
        this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }

}
