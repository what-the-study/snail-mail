package com.snailmail.back.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class BusinessException extends RuntimeException {

    private final HttpStatus status;

    protected BusinessException(ExceptionRule rule) {
        super(rule.getErrorMessage());
        this.status = rule.getStatus();
    }
}
