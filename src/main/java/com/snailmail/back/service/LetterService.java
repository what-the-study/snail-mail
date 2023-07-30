package com.snailmail.back.service;

import com.snailmail.back.domain.Letter;
import com.snailmail.back.dto.request.LetterCreateRequest;
import com.snailmail.back.dto.response.LetterResponse;
import com.snailmail.back.repository.LetterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LetterService {

    private final LetterRepository letterRepository;

    @Transactional
    public LetterResponse createLetter(LetterCreateRequest request) {
        Letter savedLetter = letterRepository.save(request.toEntity());
        savedLetter.registerReservationKey();

        return LetterResponse.fromEntity(savedLetter);
    }
}
