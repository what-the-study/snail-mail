package com.snailmail.back.dto.request;

import com.snailmail.back.domain.Letter;
import com.snailmail.back.domain.LetterStatus;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LetterCreateRequest {

    private String reservationKey;
    private String senderName;
    private String recipientName;
    private String recipientEmail;
    private Integer duration;
    private String content;
    private String password;

    public Letter toEntity(LocalDate scheduledDate) {
        return Letter.builder()
                .reservationKey(reservationKey)
                .senderName(senderName)
                .recipientName(recipientName)
                .recipientEmail(recipientEmail)
                .duration(duration)
                .scheduledDate(scheduledDate)
                .content(content)
                .password(password)
                .letterStatus(LetterStatus.SCHEDULED)
                .build();
    }
}
