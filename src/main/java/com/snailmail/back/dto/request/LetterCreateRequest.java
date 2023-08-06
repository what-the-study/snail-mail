package com.snailmail.back.dto.request;

import static com.snailmail.back.utils.DateUtil.calculateDateAfterDurationFromToday;

import com.snailmail.back.domain.Letter;
import com.snailmail.back.domain.LetterStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LetterCreateRequest {

    @NotBlank(message = "보내는 사람의 이름을 입력하세요.")
    @Size(max = 15, message = "보내는 사람의 이름은 15글자 이하로 입력하세요.")
    private String senderName;

    @NotBlank(message = "받는 사람의 이름을 입력하세요.")
    @Size(max = 15, message = "받는 사람의 이름은 15글자 이하로 입력하세요.")
    private String recipientName;

    @NotNull(message = "받는 사람의 이메일을 입력하세요.")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,}$", message = "받는 사람의 이메일 형식이 올바르지 않습니다.")
    @Size(max = 50, message = "받는 사람의 이메일은 50자 이하로 입력하세요.")
    private String recipientEmail;

    @NotNull(message = "전송 예약 기간을 입력하세요.")
    @Min(value = 30, message = "전송 예약 기간은 최소 30일 이상으로 설정해야 합니다.")
    private Integer duration;

    @NotBlank(message = "편지 내용을 입력하세요.")
    private String content;

    @NotNull(message = "비밀번호를 입력하세요.")
    @Size(min = 8, max = 20, message = "비밀번호는 8글자 이상, 20글자 이하로 입력하세요.")
    private String password;

    public Letter toEntity() {
        LocalDate scheduledDate = calculateDateAfterDurationFromToday(duration);

        return Letter.builder()
                .senderName(senderName)
                .recipientName(recipientName)
                .recipientEmail(recipientEmail)
                .duration(duration)
                .scheduledDate(scheduledDate)
                .content(content)
                .password(password)
                .letterStatus(LetterStatus.SCHEDULED)
                .build();
    }
}
