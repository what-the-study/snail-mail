package com.snailmail.back.service;

import com.snailmail.back.domain.Letter;
import com.snailmail.back.dto.request.LetterCreateRequest;
import com.snailmail.back.dto.request.LetterUpdateRequest;
import com.snailmail.back.dto.response.LetterDetailResponse;
import com.snailmail.back.dto.response.LetterReservationKeyResponse;
import com.snailmail.back.repository.LetterRepository;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LetterService {

    private final LetterRepository letterRepository;

    @Transactional
    public LetterReservationKeyResponse createLetter(LetterCreateRequest request) {
        Letter savedLetter = letterRepository.save(request.toEntity());
        savedLetter.registerReservationKey();

        return LetterReservationKeyResponse.from(savedLetter.getReservationKey());
    }

    public LetterDetailResponse findLetterByReservationKey(String reservationKey) {
        Letter letter = getLetterOrThrow(reservationKey);

        return LetterDetailResponse.fromEntity(letter);
    }

    @Transactional
    public LetterReservationKeyResponse updateLetter(String reservationKey, String password, LetterUpdateRequest request) {
        Letter letter = getLetterOrThrow(reservationKey);

        if (!password.equals(letter.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 올바르지 않습니다.");
        }

        letter.updateSenderName(request.getSenderName());
        letter.updateRecipientName(request.getRecipientName());
        letter.updateDurationAndScheduledDate(request.getDuration());
        letter.updateContent(request.getContent());

        return LetterReservationKeyResponse.from(letter.getReservationKey());
    }

    private Letter getLetterOrThrow(String reservationKey) {
        return letterRepository.findLetterByReservationKey(reservationKey)
                .orElseThrow(() -> new NoSuchElementException("해당 예약번호로 조회되는 편지가 없습니다."));
    }
}
