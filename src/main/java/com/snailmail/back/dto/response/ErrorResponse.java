package com.snailmail.back.dto.response;

import java.util.Optional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {

    private String field;
    private String message;
    private String rejectedValue;

    private ErrorResponse(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public static ErrorResponse of(String field, String message, Optional<Object> rejectedValue) {
        if (rejectedValue.isPresent()) {
            return new ErrorResponse(field, message, rejectedValue.toString());
        }

        return new ErrorResponse(field, message);
    }
}
