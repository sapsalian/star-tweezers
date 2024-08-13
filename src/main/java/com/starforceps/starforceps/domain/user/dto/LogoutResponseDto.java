package com.starforceps.starforceps.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LogoutResponseDto {
    @Schema(description = "카카오 회원 번호")
    private Long id;
}
