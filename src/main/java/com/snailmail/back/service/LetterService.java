package com.snailmail.back.service;

import com.snailmail.back.domain.Letter;
import com.snailmail.back.dto.request.LetterCreateRequest;
import com.snailmail.back.dto.response.LetterResponse;
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
    public LetterResponse createLetter(LetterCreateRequest request) {
        Letter savedLetter = letterRepository.save(request.toEntity());
        savedLetter.registerReservationKey();

        return LetterResponse.fromEntity(savedLetter);
    }

    public LetterResponse findLetterByReservationKey(String reservationKey) {
        Letter letter = letterRepository.findLetterByReservationKey(reservationKey)
                .orElseThrow(() -> new NoSuchElementException("해당 예약번호로 조회되는 편지가 없습니다."));

        return LetterResponse.fromEntity(letter);
    }
}
