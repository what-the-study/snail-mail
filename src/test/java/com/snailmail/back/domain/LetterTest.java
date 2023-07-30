package com.snailmail.back.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.snailmail.back.repository.LetterRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LetterTest {

    private final LetterRepository letterRepository;

    @Autowired
    public LetterTest(LetterRepository letterRepository) {
        this.letterRepository = letterRepository;
    }

    @Test
    @DisplayName("편지 객체 정상 생성 여부 확인")
    void saveLetterTest() {
        // given
        Letter letter = Letter.builder()
                .reservationKey("202312250001KDHFW")
                .senderName("kyle")
                .recipientName("alex")
                .recipientEmail("alex@naver.com")
                .scheduledDate(LocalDate.of(2023, 12, 25))
                .duration(30)
                .content("test content")
                .password("kyle1234")
                .letterStatus(LetterStatus.SCHEDULED)
                .createdDatetime(LocalDateTime.now())
                .updatedDatetime(LocalDateTime.now())
                .build();

        // when
        Letter savedLetter = letterRepository.save(letter);

        // then
        assertThat(savedLetter.getId()).isEqualTo(1L);
        assertThat(savedLetter.getReservationKey()).isEqualTo("202312250001KDHFW");
    }
}