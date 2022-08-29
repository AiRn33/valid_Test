package com.vaild.test.error;

import lombok.Data;
import lombok.Getter;


public enum ErrorCode {

    NOT_NULL("ERROR_CODE_0001", "필수값 누락"),
    MIN_VALUE("ERROR_CODE_0002", "최소값보다 커야합니다");

    @Getter
    private String code;
    @Getter
    private String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
