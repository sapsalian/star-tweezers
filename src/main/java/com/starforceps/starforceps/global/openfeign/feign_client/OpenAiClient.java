package com.starforceps.starforceps.global.openfeign.feign_client;

import com.starforceps.starforceps.global.openfeign.config.OpenAIFeignConfig;
import com.starforceps.starforceps.global.openfeign.dto.*;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(
        name = "openAiClient",
        url = "${openai-service.base-url}",
        configuration = OpenAIFeignConfig.class
)
public interface OpenAiClient {
    @PostMapping(value = "${openai-service.endpoints.whisper}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    WhisperResponseDto createTranscription(@RequestPart(value = "file") MultipartFile file, @RequestPart(value = "model") String model);

    @PostMapping(value = "/threads/runs", headers = {"OpenAI-Beta=assistants=v2"})
    ThreadRunResDto threadRun(@RequestBody ThreadRunReqDto threadRunReqDto);

    @GetMapping(value = "/threads/{thread_id}/messages", headers = {"OpenAI-Beta=assistants=v2"})
    MessagesResDto threadMessages(@PathVariable("thread_id") String threadId);

    @GetMapping(value = "/threads/{thread_id}/runs/{run_id}", headers = {"OpenAI-Beta=assistants=v2"})
    RunResDto getRun(@PathVariable("thread_id") String threadId, @PathVariable("run_id") String runId);
}
