package com.snailmail.back.controller;

import com.snailmail.back.dto.request.LetterCreateRequest;
import com.snailmail.back.dto.response.ApiResponse;
import com.snailmail.back.dto.response.LetterResponse;
import com.snailmail.back.service.LetterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ApiResponse<LetterResponse> createLetter(@RequestBody LetterCreateRequest request) {
        LetterResponse response = letterService.createLetter(request);

        return ApiResponse.success(response);
    }
}
