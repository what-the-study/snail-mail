package com.snailmail.back.exception;

import java.util.Map;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class BusinessException extends RuntimeException {

    private final HttpStatus status;
    private final Map<String, Object> errorCauses;

    protected BusinessException(ExceptionRule rule, Map<String, Object> errorCauses) {
        super(rule.getErrorMessage());
        this.status = rule.getStatus();
        this.errorCauses = errorCauses;
    }
}
