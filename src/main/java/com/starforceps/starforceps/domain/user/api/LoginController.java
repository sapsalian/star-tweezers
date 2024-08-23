package com.starforceps.starforceps.domain.user.api;

import com.starforceps.starforceps.domain.user.application.UserService;
import com.starforceps.starforceps.domain.user.domain.User;
import com.starforceps.starforceps.domain.user.dto.KakaoTokenResponseDto;
import com.starforceps.starforceps.domain.user.dto.LogoutResponseDto;
import com.starforceps.starforceps.domain.user.dto.SimpleUserInfoFromKakao;
import com.starforceps.starforceps.domain.user.dto.UserInfo;
import com.starforceps.starforceps.domain.user.utils.UserUtilsWithKakao;
import com.starforceps.starforceps.global.common.dto.ResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;
    private final UserUtilsWithKakao userUtilsWithKakao;

    @GetMapping("/oauth")
    public ResponseEntity<ResponseDto<TokenBundle>> loginCallback(@RequestParam String code) {
        KakaoTokenResponseDto kakaoTokenResponse = userUtilsWithKakao.getKakaoTokenResponse(code);
        String accessToken = kakaoTokenResponse.getAccess_token();
        String refreshToken = kakaoTokenResponse.getRefresh_token();

        //유저 정보 조회
        SimpleUserInfoFromKakao simpleUserInfoFromKakao = userUtilsWithKakao.getUserInfo(accessToken);
        UserInfo userInfo = UserInfo.from(simpleUserInfoFromKakao);
        Long socialId = userInfo.getSocialId();

        //회원 조회
        User user = userService.findOneBySocialId(socialId).orElse(
                //회원 저장
                userService.save(userInfo)
        );
        ResponseDto<TokenBundle> response = new ResponseDto<>(
                2001,
                "로그인 성공",
                new TokenBundle(accessToken, refreshToken)
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/oauth/logout")
    public ResponseEntity<ResponseDto<LogoutResponseDto>> logout(HttpServletRequest request) {
        LogoutResponseDto logoutBody = userUtilsWithKakao.getLogoutResponseDto(request);
        ResponseDto<LogoutResponseDto> response = new ResponseDto<>(
                2002,
                "로그아웃 성공",
                logoutBody
        );

        return ResponseEntity.ok(response);
    }


    @Data
    @AllArgsConstructor
    class TokenBundle {
        private String accessToken;
        private String refreshToken;
    }

}
