package com.starforceps.starforceps.global.common.custom_annotation.resolver;

import com.starforceps.starforceps.domain.user.domain.User;
import com.starforceps.starforceps.domain.user.dto.SimpleUserInfoFromKakao;
import com.starforceps.starforceps.domain.user.utils.UserUtilsWithKakao;
import com.starforceps.starforceps.global.common.custom_annotation.annotation.TokenId;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Map;

@RequiredArgsConstructor
public class TokenIdResolver implements HandlerMethodArgumentResolver {
    private final UserUtilsWithKakao userUtilsWithKakao;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(TokenId.class);
    }

    @Override
    public Long resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String token = webRequest.getHeader("Authorization").substring(7);

        SimpleUserInfoFromKakao simpleUserInfoDto = userUtilsWithKakao.getUserInfo(token);

        return simpleUserInfoDto.getId();
    }
}
