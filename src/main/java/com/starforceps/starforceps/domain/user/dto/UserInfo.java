package com.starforceps.starforceps.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfo {
    @Schema(description = "카카오 회원 번호")
    private Long socialId;
    private String nickname;

    public static UserInfo from(SimpleUserInfoFromKakao dto) {
        return new UserInfo(
                dto.getId(),
                dto.getProperties().getNickname()
        );
    }
}
