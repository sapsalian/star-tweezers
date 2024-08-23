package com.starforceps.starforceps.domain.user.api;

import com.starforceps.starforceps.domain.user.application.UserService;
import com.starforceps.starforceps.domain.user.domain.User;
import com.starforceps.starforceps.domain.user.dto.SimpleUserInfoFromKakao;
import com.starforceps.starforceps.domain.user.dto.UserResponseDto;
import com.starforceps.starforceps.domain.user.utils.UserUtils;
import com.starforceps.starforceps.domain.user.utils.UserUtilsWithKakao;
import com.starforceps.starforceps.global.common.dto.ResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserUtilsWithKakao userUtilsWithKakao;
    private final UserService userService;
    @GetMapping("/users")
    public ResponseEntity<ResponseDto<UserResponseDto>> getUser(HttpServletRequest request) {
        log.info("getUserController");
        String accessToken = UserUtils.getAccessToken(request);
        log.info("accessToken={}", accessToken);
        SimpleUserInfoFromKakao userInfo = userUtilsWithKakao.getUserInfo(accessToken);
        log.info("userInfo={}", userInfo);
        User user = userService.findBySocialId(userInfo.getId());
        log.info("user.nickname={}", user.getNickname());
        UserResponseDto userResponseDto = UserResponseDto.from(user);
        log.info("userResponseDto={}", userResponseDto.getNickname());
        ResponseDto<UserResponseDto> response = new ResponseDto<>(
                2003,
                "유저 조회 성공",
                userResponseDto
        );
        return ResponseEntity.ok(response);
    }

}