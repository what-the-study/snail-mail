package com.snailmail.back.service;

import static com.snailmail.back.exception.ExceptionRule.INVALID_PASSWORD;
import static com.snailmail.back.exception.ExceptionRule.NOT_FOUND_LETTER_BY_RESERVATION_KEY;

import com.snailmail.back.domain.Letter;
import com.snailmail.back.dto.request.LetterCreateRequestDto;
import com.snailmail.back.dto.request.LetterUpdateRequestDto;
import com.snailmail.back.dto.response.LetterDetailResponseDto;
import com.snailmail.back.dto.response.LetterReservationKeyResponseDto;
import com.snailmail.back.exception.LetterException;
import com.snailmail.back.repository.LetterRepository;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LetterService {

    private final LetterRepository letterRepository;

    @Transactional
    public LetterReservationKeyResponseDto createLetter(LetterCreateRequestDto request) {
        Letter savedLetter = letterRepository.save(request.toEntity());
        savedLetter.registerReservationKey();

        return LetterReservationKeyResponseDto.from(savedLetter.getReservationKey());
    }

    public LetterDetailResponseDto findLetterByReservationKey(String reservationKey) {
        Letter letter = getLetterOrThrow(reservationKey);

        return LetterDetailResponseDto.fromEntity(letter);
    }

    @Transactional
    public LetterReservationKeyResponseDto updateLetter(String reservationKey, String password, LetterUpdateRequestDto request) {
        Letter letter = getLetterOrThrow(reservationKey);
        validateOriginalPasswordEqualsInputPassword(letter.getPassword(), password);

        letter.updateSenderName(request.getSenderName());
        letter.updateRecipientName(request.getRecipientName());
        letter.updateDurationAndScheduledDate(request.getDuration());
        letter.updateContent(request.getContent());

        return LetterReservationKeyResponseDto.from(reservationKey);
    }

    @Transactional
    public LetterReservationKeyResponseDto deleteLetterByReservationKey(String reservationKey, String password) {
        Letter letter = getLetterOrThrow(reservationKey);
        validateOriginalPasswordEqualsInputPassword(letter.getPassword(), password);

        letterRepository.delete(letter);

        return LetterReservationKeyResponseDto.from(reservationKey);
    }

    private Letter getLetterOrThrow(String reservationKey) {
        return letterRepository.findLetterByReservationKey(reservationKey)
                .orElseThrow(() -> new LetterException(NOT_FOUND_LETTER_BY_RESERVATION_KEY, Map.of("reservationKey", reservationKey)));
    }

    private void validateOriginalPasswordEqualsInputPassword(String originalPassword, String inputPassword) {
        if (!originalPassword.equals(inputPassword)) {
            throw new LetterException(INVALID_PASSWORD, Map.of("password", inputPassword));
        }
    }
}
