package com.example.errorhandler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // Common
    INVALID_INPUT_VALUE(400, "C001", "Invalid Input Value"),
    INTERNAL_SERVER_ERROR(500, "C002", "Server Error"),
    METHOD_NOT_ALLOWED(405, "C002", "지원하지 않은 HTTP Method 이다.")
    ;

    private final int status;
    private final String code;  // HTTP Status Code
    private final String message;
}
