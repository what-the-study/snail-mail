package com.snailmail.back.dto.request;

import com.snailmail.back.domain.Letter;
import com.snailmail.back.domain.LetterStatus;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LetterCreateRequest {

    private String senderName;
    private String recipientName;
    private String recipientEmail;
    private Integer duration;
    private String content;
    private String password;

    public Letter toEntity() {
        return Letter.builder()
                .senderName(senderName)
                .recipientName(recipientName)
                .recipientEmail(recipientEmail)
                .duration(duration)
                .scheduledDate(getScheduledDate())
                .content(content)
                .password(password)
                .letterStatus(LetterStatus.SCHEDULED)
                .build();
    }

    private LocalDate getScheduledDate() {
        LocalDate today = LocalDate.now();

        return today.plusDays(duration);
    }
}
