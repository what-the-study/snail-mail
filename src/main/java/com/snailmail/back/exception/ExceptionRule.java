package com.snailmail.back.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionRule {

    NOT_FOUND_LETTER_BY_RESERVATION_KEY(HttpStatus.NOT_FOUND, "예약번호에 해당하는 편지를 찾을 수 없습니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 올바르지 않습니다."),

    BAD_REQUEST_400(HttpStatus.BAD_REQUEST, "사용자 입력이 올바르지 않습니다."),
    NOT_FOUND_404(HttpStatus.NOT_FOUND, "올바르지 않은 URL에 대한 요청입니다."),
    METHOD_NOT_ALLOWED_405(HttpStatus.METHOD_NOT_ALLOWED, "허용되지 않은 HTTP Method 요청입니다."),
    INTERNAL_SERVER_ERROR_500(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 에러가 발생했습니다. 이용에 불편을 드려 죄송합니다.");

    private final HttpStatus status;
    private final String errorMessage;
}
