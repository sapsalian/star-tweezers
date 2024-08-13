package com.starforceps.starforceps.global.config;

import com.starforceps.starforceps.domain.user.utils.UserUtilsWithKakao;
import com.starforceps.starforceps.global.common.custom_annotation.resolver.TokenIdResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class ResolverConfig implements WebMvcConfigurer {
    private final UserUtilsWithKakao userUtilsWithKakao;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new TokenIdResolver(userUtilsWithKakao));
    }
}
