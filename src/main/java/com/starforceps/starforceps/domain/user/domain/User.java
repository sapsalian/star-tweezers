package com.starforceps.starforceps.domain.user.domain;

import com.starforceps.starforceps.domain.user.dto.UserInfo;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String nickname;
    @Column(name = "social_id")
    private Long socialId;

    protected User(){}

    private User( Long social_id,String nickname){
        this.socialId = social_id;
        this.nickname = nickname;
    }

    public static User from(UserInfo userInfo) {
        return new User(
                userInfo.getSocialId(),
                userInfo.getNickname()
        );
    }
}
