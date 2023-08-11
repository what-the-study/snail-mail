package com.snailmail.back.controller;

import com.snailmail.back.dto.request.LetterCreateRequestDto;
import com.snailmail.back.dto.request.LetterUpdateRequestDto;
import com.snailmail.back.dto.response.ApiResponseDto;
import com.snailmail.back.dto.response.LetterDetailResponseDto;
import com.snailmail.back.dto.response.LetterReservationKeyResponseDto;
import com.snailmail.back.service.LetterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/letters")
@RequiredArgsConstructor
public class LetterController {

    private final LetterService letterService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponseDto<LetterReservationKeyResponseDto> createLetter(@Valid @RequestBody LetterCreateRequestDto request) {
        LetterReservationKeyResponseDto response = letterService.createLetter(request);

        return ApiResponseDto.success(response);
    }

    @GetMapping("/{reservationKey}")
    public ApiResponseDto<LetterDetailResponseDto> findLetterByReservationKey(@PathVariable String reservationKey) {
        LetterDetailResponseDto response = letterService.findLetterByReservationKey(reservationKey);

        return ApiResponseDto.success(response);
    }

    @PatchMapping("/{reservationKey}")
    public ApiResponseDto<LetterReservationKeyResponseDto> updateLetterByReservationKey(
            @PathVariable String reservationKey,
            @RequestHeader String password,
            @Valid @RequestBody LetterUpdateRequestDto request) {
        LetterReservationKeyResponseDto response = letterService.updateLetter(reservationKey, password, request);

        return ApiResponseDto.success(response);
    }

    @DeleteMapping("/{reservationKey}")
    public ApiResponseDto<LetterReservationKeyResponseDto> deleteLetterByReservationKey(
            @PathVariable String reservationKey,
            @RequestHeader String password) {
        LetterReservationKeyResponseDto response = letterService.deleteLetterByReservationKey(reservationKey, password);

        return ApiResponseDto.success(response);
    }

}
