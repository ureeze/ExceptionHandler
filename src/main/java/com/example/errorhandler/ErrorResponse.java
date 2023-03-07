package com.example.errorhandler;

import lombok.Getter;
import org.springframework.validation.BindingResult;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    private ErrorResponse(ErrorCode code, List<FieldError> of) {
        this.message = code.getMessage();
        this.status = code.getStatus();
        this.errors = of;
        this.code = code.getCode();
        this.timestamp = LocalDateTime.now();
    }

    public static ErrorResponse of(ErrorCode code) {
        return new ErrorResponse(code.getMessage(), code.getStatus(), code.getCode());
    }

    public static ErrorResponse of(Exception e) {
        return new ErrorResponse(e.getMessage(), 500, "C002");
    }

    public static ErrorResponse of(ErrorCode code, BindingResult e) {
        return new ErrorResponse(code, FieldError.of(e));
    }


    @Getter
    public static class FieldError {
        private String field;
        private String value;
        private String reason;

        public FieldError(String field, String value, String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }

        public static List<FieldError> of(BindingResult bindingResult) {
            return bindingResult.getFieldErrors()
                    .stream()
                    .map(error -> new FieldError(
                            error.getField(),
                            error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()))
                    .collect(Collectors.toList());
        }
    }
}