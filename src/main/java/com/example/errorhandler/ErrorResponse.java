package com.example.errorhandler;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ErrorResponse {
    private String message;
    private int status;
    private List<FieldError> errors;
    private String code;
    private LocalDateTime timestamp;

    private ErrorResponse(String message, int status, String code) {
        this.message = message;
        this.status = status;
        this.errors = new ArrayList<>();
        this.code = code;
        this.timestamp = LocalDateTime.now();
    }

    public static ErrorResponse of(ErrorCode code) {
        return new ErrorResponse(code.getMessage(), code.getStatus(), code.getCode());
    }

    public static ErrorResponse of(Exception e) {
        return new ErrorResponse(e.getMessage(), 500, "C002");
    }


    @Getter
    public static class FieldError {
        private String field;
        private String value;
        private String reason;
    }
}