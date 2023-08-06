package com.snailmail.back.service;

import static com.snailmail.back.exception.ExceptionRule.INVALID_PASSWORD;
import static com.snailmail.back.exception.ExceptionRule.NOT_FOUND_LETTER_BY_RESERVATION_KEY;

import com.snailmail.back.domain.Letter;
import com.snailmail.back.dto.request.LetterCreateRequest;
import com.snailmail.back.dto.request.LetterUpdateRequest;
import com.snailmail.back.dto.response.LetterDetailResponse;
import com.snailmail.back.dto.response.LetterReservationKeyResponse;
import com.snailmail.back.exception.LetterException;
import com.snailmail.back.repository.LetterRepository;
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
        validateOriginalPasswordEqualsInputPassword(letter.getPassword(), password);

        letter.updateSenderName(request.getSenderName());
        letter.updateRecipientName(request.getRecipientName());
        letter.updateDurationAndScheduledDate(request.getDuration());
        letter.updateContent(request.getContent());

        return LetterReservationKeyResponse.from(reservationKey);
    }

    @Transactional
    public LetterReservationKeyResponse deleteLetterByReservationKey(String reservationKey, String password) {
        Letter letter = getLetterOrThrow(reservationKey);
        validateOriginalPasswordEqualsInputPassword(letter.getPassword(), password);

        letterRepository.delete(letter);

        return LetterReservationKeyResponse.from(reservationKey);
    }

    private Letter getLetterOrThrow(String reservationKey) {
        return letterRepository.findLetterByReservationKey(reservationKey)
                .orElseThrow(() -> new LetterException(NOT_FOUND_LETTER_BY_RESERVATION_KEY));
    }

    private void validateOriginalPasswordEqualsInputPassword(String originalPassword, String inputPassword) {
        if (!originalPassword.equals(inputPassword)) {
            throw new LetterException(INVALID_PASSWORD);
        }
    }
}
