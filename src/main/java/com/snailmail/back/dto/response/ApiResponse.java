package com.snailmail.back.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApiResponse<T> {

    private String code;
    private T data;
    private String message;

    protected ApiResponse(String code, T data) {
        this.code = code;
        this.data = data;
    }

    protected ApiResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>("success", data);
    }

    public static ApiResponse<Void> fail(String message) {
        return new ApiResponse<>("fail", message);
    }
}
