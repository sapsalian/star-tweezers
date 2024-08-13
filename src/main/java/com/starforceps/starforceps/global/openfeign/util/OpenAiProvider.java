package com.starforceps.starforceps.global.openfeign.util;

import com.starforceps.starforceps.global.openfeign.dto.*;
import com.starforceps.starforceps.global.openfeign.feign_client.OpenAiClient;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class OpenAiProvider {

    private final OpenAiClient openAiClient;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public OpenAiProvider(OpenAiClient openAiClient) {
        this.openAiClient = openAiClient;
    }

    public String audioToText(MultipartFile audioFile) {
        WhisperResponseDto whisperResponseDto = openAiClient.createTranscription(audioFile, "whisper-1");

        return whisperResponseDto.text();
    }

    public String askToGpt(String assistantsId, String fullText) {
        ThreadRunReqDto threadRunReqDto = ThreadRunReqDto.of(
                assistantsId,
                "user",
                fullText
        );

        ThreadRunResDto threadRunResDto = openAiClient.threadRun(threadRunReqDto);

        CountDownLatch latch = new CountDownLatch(1);

        scheduler.scheduleAtFixedRate(() -> {
            RunResDto runResDto = openAiClient.getRun(threadRunResDto.thread_id(), threadRunResDto.id());

            if (runResDto.status().equals("completed")) {
                latch.countDown();
                scheduler.shutdown();
            }
        }, 0, 1, TimeUnit.SECONDS);

        try {
            latch.await();
        } catch (InterruptedException e) {

        }

        MessagesResDto messagesResDto = openAiClient.threadMessages(threadRunResDto.thread_id());
        return messagesResDto.getReply();
    }
}
