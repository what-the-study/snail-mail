package com.snailmail.back.utils;

import java.time.LocalDate;

public final class DateUtil {

    public static LocalDate calculateDateAfterDurationFromToday(Integer duration) {
        LocalDate today = LocalDate.now();

        return today.plusDays(duration);
    }
}
