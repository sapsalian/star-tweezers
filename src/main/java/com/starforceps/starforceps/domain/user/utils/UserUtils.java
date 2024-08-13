package com.starforceps.starforceps.domain.user.utils;

import jakarta.servlet.http.HttpServletResponse;

public class UserUtils {
    private static String getAccessToken(HttpServletResponse response) {
        String authorization = response.getHeader("Authorization");
        if(authorization == null || !authorization.startsWith("Bearer ")) {
            throw new RuntimeException("Authorization header is incorrect");
        }
        return response.getHeader("Authorization").substring(7);
    }
}
