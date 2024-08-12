package com.starforceps.starforceps.global.openfeign.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAIFeignConfig {

    @Value("${openai-service.api-key}")
    private String openAiKey;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) { // 여기에 실제 Bearer 토큰을 설정합니다.
                requestTemplate.header("Authorization", "Bearer " + openAiKey);
            }
        };
    }
}
