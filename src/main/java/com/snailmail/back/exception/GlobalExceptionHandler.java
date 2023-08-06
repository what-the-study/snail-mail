package com.snailmail.back.exception;

import static com.snailmail.back.exception.ExceptionRule.INTERNAL_SERVER_ERROR_500;
import static com.snailmail.back.exception.ExceptionRule.METHOD_NOT_ALLOWED_405;
import static com.snailmail.back.exception.ExceptionRule.NOT_FOUND_404;

import com.snailmail.back.dto.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNoHandlerFoundException(NoHandlerFoundException e) {
        log.error("올바르지 않은 URL 요청 : {}", e.getMessage(), e);
        ApiResponse<Void> response = ApiResponse.fail(NOT_FOUND_404.getErrorMessage());

        return ResponseEntity.status(NOT_FOUND_404.getStatus()).body(response);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponse<Void>> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("허용되지 않은 HTTP Method 요청 : {}", e.getMessage(), e);
        ApiResponse<Void> response = ApiResponse.fail(METHOD_NOT_ALLOWED_405.getErrorMessage());

        return ResponseEntity.status(METHOD_NOT_ALLOWED_405.getStatus()).body(response);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Void>> handleBusinessException(BusinessException e) {
        log.error("사용자 입력 예외 발생 : {} | {}", e.getMessage(), e.getErrorCauses(), e);
        ApiResponse<Void> response = ApiResponse.fail(e.getMessage());

        return ResponseEntity.status(e.getStatus()).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception e) {
        log.error("알 수 없는 예외 발생 : {}", e.getMessage(), e);
        ApiResponse<Void> response = ApiResponse.fail(INTERNAL_SERVER_ERROR_500.getErrorMessage());

        return ResponseEntity.status(INTERNAL_SERVER_ERROR_500.getStatus()).body(response);
    }
}
