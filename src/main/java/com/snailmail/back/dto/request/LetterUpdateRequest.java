package com.snailmail.back.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LetterUpdateRequest {

    private String senderName;
    private String recipientName;
    private Integer duration;
    private String content;
}
