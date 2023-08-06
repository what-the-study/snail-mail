package com.snailmail.back.domain;

import static com.snailmail.back.utils.DateUtil.calculateDateAfterDurationFromToday;

import com.snailmail.back.utils.StringUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.text.MessageFormat;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "letter")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Letter extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true, length = 100)
    private String reservationKey;

    @Column(nullable = false, length = 15)
    private String senderName;

    @Column(nullable = false, length = 15)
    private String recipientName;

    @Column(nullable = false, length = 50)
    private String recipientEmail;

    @Column(nullable = false)
    private LocalDate scheduledDate;

    @Column(nullable = false)
    private Integer duration;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false, length = 20)
    private String password;

    @Enumerated(value = EnumType.STRING)
    private LetterStatus letterStatus;

    @Builder
    private Letter(String reservationKey, String senderName, String recipientName,
            String recipientEmail, LocalDate scheduledDate, Integer duration,
            String content, String password, LetterStatus letterStatus) {
        this.reservationKey = reservationKey;
        this.senderName = senderName;
        this.recipientName = recipientName;
        this.recipientEmail = recipientEmail;
        this.scheduledDate = scheduledDate;
        this.duration = duration;
        this.content = content;
        this.password = password;
        this.letterStatus = letterStatus;
    }

    public void registerReservationKey() {
        String prefix = StringUtil.localDateToString(LocalDate.now());
        String infix = StringUtil.fillZero(id);
        String suffix = StringUtil.getRandomUppercaseString();

        this.reservationKey = MessageFormat.format("{0}{1}{2}", prefix, infix, suffix);
    }

    public void updateSenderName(String senderName) {
        this.senderName = senderName;
    }

    public void updateRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public void updateDurationAndScheduledDate(Integer duration) {
        this.duration = duration;
        this.scheduledDate = calculateDateAfterDurationFromToday(duration);
    }

    public void updateContent(String content) {
        this.content = content;
    }
}
