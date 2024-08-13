package com.starforceps.starforceps.domain.user.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UserUtils {
    public static String getAccessToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if(authorization == null || !authorization.startsWith("Bearer ")) {
            throw new RuntimeException("Authorization header is incorrect");
        }
        return request.getHeader("Authorization").substring(7);
    }
}
