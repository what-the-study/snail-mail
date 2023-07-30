package com.snailmail.back.utils;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

public final class StringUtil {

    private static final String DATE_SPLIT_REGEX = "-";

    private static final String ZERO_FILL_FORMAT = "%04d";

    private static final Integer FIRST_UPPER_ALPHABET_ASCII = 65;
    private static final Integer LAST_UPPER_ALPHABET_ASCII = 91;
    private static final Integer RANDOM_STRING_SIZE = 5;

    public static String localDateToString(LocalDate localDate) {
        String[] localDateStrings = localDate.toString().split(DATE_SPLIT_REGEX);

        return Arrays.stream(localDateStrings)
                .collect(Collectors.joining());
    }

    public static String fillZero(Long target) {
        return String.format(ZERO_FILL_FORMAT, target);
    }

    public static String getRandomUppercaseString() {
        Random random = new Random();

        return random.ints(FIRST_UPPER_ALPHABET_ASCII, LAST_UPPER_ALPHABET_ASCII)
                .limit(RANDOM_STRING_SIZE)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

}
