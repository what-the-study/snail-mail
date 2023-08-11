package com.snailmail.back.dto.response;

import java.util.Optional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponseDto {

    private String field;
    private String message;
    private String rejectedValue;

    private ErrorResponseDto(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public static ErrorResponseDto of(String field, String message, Optional<Object> rejectedValue) {
        if (rejectedValue.isPresent()) {
            return new ErrorResponseDto(field, message, rejectedValue.toString());
        }

        return new ErrorResponseDto(field, message);
    }
}
