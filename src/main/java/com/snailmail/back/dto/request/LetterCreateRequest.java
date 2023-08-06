package com.snailmail.back.dto.request;

import static com.snailmail.back.utils.DateUtil.calculateDateAfterDurationFromToday;

import com.snailmail.back.domain.Letter;
import com.snailmail.back.domain.LetterStatus;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LetterCreateRequest {

    private String senderName;
    private String recipientName;
    private String recipientEmail;
    private Integer duration;
    private String content;
    private String password;

    public Letter toEntity() {
        LocalDate scheduledDate = calculateDateAfterDurationFromToday(duration);

        return Letter.builder()
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
