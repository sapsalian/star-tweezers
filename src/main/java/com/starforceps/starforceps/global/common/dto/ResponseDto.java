package com.starforceps.starforceps.global.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record ResponseDto<T>(
        @Schema(description = "커스텀 상태 코드",example = "2001")
        int code,

        @Schema(description = "상태 메세지",example = "예시 상태메세지입니다.")
        String statusMsg,

        @Schema(description = "응답 생성 일시", example = "2024-09-15 14:30")
        LocalDateTime timeStamp,

        T data
) {
        public ResponseDto(int code, String statusMsg, T data) {
                this(
                        code,
                        statusMsg,
                        LocalDateTime.now(),
                        data
                );
        }
}
