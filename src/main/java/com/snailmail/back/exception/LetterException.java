package com.snailmail.back.exception;

import java.util.Map;

public class LetterException extends BusinessException {

    public LetterException(ExceptionRule rule, Map<String, Object> errorCauses) {
        super(rule, errorCauses);
    }
}
