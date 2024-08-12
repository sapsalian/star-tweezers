package com.starforceps.starforceps.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfo {
    private Long socialId;
    private String nickname;

    public static UserInfo from(SimpleUserInfoDto dto) {
        return new UserInfo(
                dto.getId(),
                dto.getProperties().getNickname()
        );
    }
}
