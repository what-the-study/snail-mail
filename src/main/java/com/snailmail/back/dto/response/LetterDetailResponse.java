package com.snailmail.back.dto.response;

import com.snailmail.back.domain.Letter;
import com.snailmail.back.domain.LetterStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class LetterDetailResponse {

    private Long id;
    private String reservationKey;
    private String senderName;
    private String recipientName;
    private String recipientEmail;
    private Integer duration;
    private LocalDate scheduledDate;
    private String content;
    private LetterStatus letterStatus;
    private LocalDateTime createdDatetime;
    private LocalDateTime updatedDatetime;

    public static LetterDetailResponse fromEntity(Letter letter) {
        return LetterDetailResponse.builder()
                .id(letter.getId())
                .reservationKey(letter.getReservationKey())
                .senderName(letter.getSenderName())
                .recipientName(letter.getRecipientName())
                .recipientEmail(letter.getRecipientEmail())
                .duration(letter.getDuration())
                .scheduledDate(letter.getScheduledDate())
                .content(letter.getContent())
                .letterStatus(letter.getLetterStatus())
                .createdDatetime(letter.getCreatedDatetime())
                .updatedDatetime(letter.getUpdatedDatetime())
                .build();
    }
}
