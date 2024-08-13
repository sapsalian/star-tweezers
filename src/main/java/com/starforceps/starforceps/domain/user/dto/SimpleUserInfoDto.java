package com.starforceps.starforceps.domain.user.dto;

import lombok.Data;

@Data
public class SimpleUserInfoDto {
    private Long id;
    private UserProperties properties;

    @Data
    class UserProperties {
        private String nickname;
    }
}
