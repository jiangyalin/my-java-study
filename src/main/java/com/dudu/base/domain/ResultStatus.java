package com.dudu.base.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ResultStatus {
    OK(200),
    UN_AUTH(401),
    REQUEST_ERROR(400),
    REMOTE_EXCEPTION(501),
    ERROR(500);

    private final int value;

    ResultStatus(int value) {
        this.value = value;
    }

    @JsonValue
    public int getValue() {
        return this.value;
    }

    @JsonCreator
    public static ResultStatus formValue(int code) {
        for (ResultStatus c: ResultStatus.values()) {
            if (c.value == code) {
                return c;
            }
        }
        throw new IllegalArgumentException("Invalid ResultStatus code: " + code);
    }
}
