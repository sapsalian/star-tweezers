package com.starforceps.starforceps.domain.user.api;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
    @GetMapping("/users")
    public void getUser(HttpServletResponse response) {
        getAccessToken(response);

    }

    private String getAccessToken(HttpServletResponse response) {
        String authorization = response.getHeader("Authorization");
        if(authorization == null || !authorization.startsWith("Bearer ")) {
            throw new RuntimeException("Authorization header is incorrect");
        }
        return response.getHeader("Authorization").substring(7);
    }
}