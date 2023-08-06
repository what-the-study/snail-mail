package com.snailmail.back.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LetterUpdateRequest {

    private String senderName;
    private String recipientName;
    private Integer duration;
    private String content;
}
