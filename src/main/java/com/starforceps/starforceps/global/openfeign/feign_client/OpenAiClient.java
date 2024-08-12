package com.starforceps.starforceps.global.openfeign.feign_client;

import com.starforceps.starforceps.global.openfeign.config.OpenAIFeignConfig;
import com.starforceps.starforceps.global.openfeign.dto.WhisperResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(
        name = "openAiClient",
        url = "${openai-service.base-url}",
        configuration = OpenAIFeignConfig.class
)
public interface OpenAiClient {
    @PostMapping(value = "${openai-service.endpoints.whisper}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    WhisperResponseDto createTranscription(@RequestPart(value = "file") MultipartFile file, @RequestPart(value = "model") String model);
}
