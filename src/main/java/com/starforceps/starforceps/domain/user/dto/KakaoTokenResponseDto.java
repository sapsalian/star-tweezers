package com.starforceps.starforceps.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class KakaoTokenResponseDto {
    private String access_token;
    private String token_type;
    private int expires_in;
    private String refresh_token;
    private String scope;
}
