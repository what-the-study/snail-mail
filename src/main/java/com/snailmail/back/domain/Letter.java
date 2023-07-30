package com.snailmail.back.domain;

import com.snailmail.back.utils.StringUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "letters")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Letter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Lob
    private String content;

    @Column(nullable = false)
    private String password;

    @Enumerated(value = EnumType.STRING)
    private LetterStatus letterStatus;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDatetime;

    @LastModifiedDate
    private LocalDateTime updatedDatetime;

    @Builder
    public Letter(String reservationKey, String senderName, String recipientName,
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
}
