package com.snailmail.back.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class StringUtilTest {

    @ParameterizedTest
    @DisplayName("날짜 데이터를 넣었을 때, 년월일 숫자만 문자열로 파싱하는지 확인한다.")
    @CsvSource({"2023-07-12,20230712", "2014-05-12,20140512", "2020-09-13,20200913"})
    void localDateToStringTest(String input, String expected) {
        LocalDate localDate = LocalDate.parse(input);
        String actual = StringUtil.localDateToString(localDate);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("정수를 넣었을 때, 비어있는 부분만큼 0을 채워서 4자리 문자열로 반환하는지 확인한다.")
    @CsvSource({"1,0001", "20,0020", "234,0234", "1234,1234"})
    void fillZeroTest(Long input, String expected) {
        String actual = StringUtil.fillZero(input);

        assertThat(actual).isEqualTo(expected);
    }
}