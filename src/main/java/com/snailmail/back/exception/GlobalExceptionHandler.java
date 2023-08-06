package com.snailmail.back.exception;

import static com.snailmail.back.exception.ExceptionRule.BAD_REQUEST_400;
import static com.snailmail.back.exception.ExceptionRule.INTERNAL_SERVER_ERROR_500;
import static com.snailmail.back.exception.ExceptionRule.METHOD_NOT_ALLOWED_405;
import static com.snailmail.back.exception.ExceptionRule.NOT_FOUND_404;

import com.snailmail.back.dto.response.ApiResponse;
import com.snailmail.back.dto.response.ErrorResponse;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<List<ErrorResponse>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("사용자 입력 예외 발생 : {}", e.getMessage(), e);
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();

        List<ErrorResponse> errorData = allErrors.stream()
                .map(FieldError.class::cast)
                .map(fieldError -> ErrorResponse.of(
                        fieldError.getField(),
                        fieldError.getDefaultMessage(),
                        Optional.ofNullable(fieldError.getRejectedValue())))
                .toList();

        ApiResponse<List<ErrorResponse>> response = ApiResponse.fail(errorData, BAD_REQUEST_400.getErrorMessage());

        return ResponseEntity.status(BAD_REQUEST_400.getStatus()).body(response);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Void>> handleBusinessException(BusinessException e) {
        log.error("비즈니스 로직 예외 발생 : {} | {}", e.getMessage(), e.getErrorCauses(), e);
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
