package com.snailmail.back.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponseDto<T> {

    private String code;
    private T data;
    private String message;

    protected ApiResponseDto(String code, T data) {
        this.code = code;
        this.data = data;
    }

    protected ApiResponseDto(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static <T> ApiResponseDto<T> success(T data) {
        return new ApiResponseDto<>("success", data);
    }

    public static ApiResponseDto<Void> fail(String message) {
        return new ApiResponseDto<>("fail", message);
    }

    public static <T> ApiResponseDto<T> fail(T data, String message) {
        return new ApiResponseDto<>("fail", data, message);
    }
}
