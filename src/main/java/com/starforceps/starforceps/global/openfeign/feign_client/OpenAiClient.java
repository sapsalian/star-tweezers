package com.starforceps.starforceps.global.openfeign.feign_client;

import com.starforceps.starforceps.global.openfeign.config.OpenAIFeignConfig;
import com.starforceps.starforceps.global.openfeign.dto.WhisperRequestDto;
import com.starforceps.starforceps.global.openfeign.dto.WhisperResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "openAiClient",
        url = "${openai-service.base-url}",
        configuration = OpenAIFeignConfig.class
)
public interface OpenAiClient {
    @PostMapping(value = "${openai-service.endpoints.whisper}", headers = {"Content-Type=multipart/form-data"})
    WhisperResponseDto createTranscription(@ModelAttribute WhisperRequestDto whisperRequestDto);
}
