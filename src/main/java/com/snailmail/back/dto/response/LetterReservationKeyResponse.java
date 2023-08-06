package com.snailmail.back.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "from")
public class LetterReservationKeyResponse {

    private String reservationKey;
}
