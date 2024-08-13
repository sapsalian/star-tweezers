package com.starforceps.starforceps.domain.user.utils;

import com.starforceps.starforceps.domain.user.dto.KakaoTokenResponseDto;
import com.starforceps.starforceps.domain.user.dto.LogoutResponseDto;
import com.starforceps.starforceps.domain.user.dto.SimpleUserInfoDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class UserUtilsWithKakao {
    @Value("${REDIRECT_URI}")
    private String REDIRECT_URI ;
    @Value("${REST_API_KEY}")
    private String REST_API_KEY;
    @Value("${KAKAO_TOKEN_URL}")
    private String KAKAO_TOKEN_URL;
    @Value("${KAKAO_USER_INFO_URL}")
    private String KAKAO_USER_INFO_URL;
    @Value("${KAKAO_LOGOUT_URL}")
    private String KAKAO_LOGOUT_URL;

    // 인가 코드로 카카오로부터 토큰 받는 함수
    public ResponseEntity<KakaoTokenResponseDto> getKakaoTokenResponse(String code) {
        RestTemplate restTemplate = new RestTemplate();
        System.out.println(REDIRECT_URI);
        System.out.println(KAKAO_TOKEN_URL);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("code", code);
        body.add("redirect_uri", REDIRECT_URI);
        body.add("client_id", REST_API_KEY);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);

        // POST 요청 보내기
        ResponseEntity<KakaoTokenResponseDto> response = restTemplate.exchange(
                KAKAO_TOKEN_URL,
                HttpMethod.POST,
                requestEntity,
                KakaoTokenResponseDto.class
        );
        return response;
    }

    //엑세스토큰으로 카카오로부터 사용자 정보 조회
    public SimpleUserInfoDto getUserInfo(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();

        // 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        // HTTP 요청 엔티티 생성
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        // GET 요청 실행
        ResponseEntity<SimpleUserInfoDto> response = restTemplate.exchange(
                KAKAO_USER_INFO_URL,
                HttpMethod.GET,
                requestEntity,
                SimpleUserInfoDto.class
        );

        // 응답 본문 반환
        return response.getBody();
    }

    public LogoutResponseDto getLogoutResponseDto(HttpServletRequest request) {
        String accessToken = UserUtils.getAccessToken(request);;
        RestTemplate restTemplate = new RestTemplate();

        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/x-www-form-urlencoded");
        headers.set("Authorization", "Bearer " + accessToken);

        // HttpEntity를 사용하여 헤더 포함
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        // POST 요청 실행
        ResponseEntity<LogoutResponseDto> logoutResponse = restTemplate.exchange(
                KAKAO_LOGOUT_URL,
                HttpMethod.POST,
                entity,
                LogoutResponseDto.class
        );

        return logoutResponse.getBody();
    }
}
