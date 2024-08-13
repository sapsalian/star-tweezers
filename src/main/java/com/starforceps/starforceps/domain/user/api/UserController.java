package com.starforceps.starforceps.domain.user.api;

import com.starforceps.starforceps.domain.user.utils.UserUtils;
import com.starforceps.starforceps.domain.user.utils.UserUtilsWithKakao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private UserUtilsWithKakao userUtilsWithKakao;
    @GetMapping("/users")
    public void getUser(HttpServletRequest request) {
        String accessToken = UserUtils.getAccessToken(request);
        userUtilsWithKakao.getUserInfo(accessToken);
    }

}