package com.snailmail.back.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LetterUpdateRequest {

    @NotBlank(message = "보내는 사람의 이름을 입력하세요.")
    @Size(max = 15, message = "보내는 사람의 이름은 15글자 이하로 입력하세요.")
    private String senderName;

    @NotBlank(message = "받는 사람의 이름을 입력하세요.")
    @Size(max = 15, message = "받는 사람의 이름은 15글자 이하로 입력하세요.")
    private String recipientName;

    @NotNull(message = "전송 예약 기간을 입력하세요.")
    @Min(value = 30, message = "전송 예약 기간은 최소 30일 이상으로 설정해야 합니다.")
    private Integer duration;

    @NotBlank(message = "편지 내용을 입력하세요.")
    private String content;
}
