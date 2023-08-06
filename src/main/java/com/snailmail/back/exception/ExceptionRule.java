package com.snailmail.back.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionRule {

    NOT_FOUND_LETTER_BY_RESERVATION_KEY(HttpStatus.NOT_FOUND, "예약번호에 해당하는 편지를 찾을 수 없습니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 올바르지 않습니다.");

    private final HttpStatus status;
    private final String errorMessage;
}
