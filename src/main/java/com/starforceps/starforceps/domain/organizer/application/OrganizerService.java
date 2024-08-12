package com.starforceps.starforceps.domain.organizer.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.starforceps.starforceps.domain.organizer.dao.OrganizerRepository;
import com.starforceps.starforceps.domain.organizer.dto.OrganizerRequestDto;
import com.starforceps.starforceps.global.openfeign.dto.*;
import com.starforceps.starforceps.global.openfeign.feign_client.OpenAiClient;
import io.swagger.v3.core.util.Json;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class OrganizerService {
    private final OrganizerRepository organizerRepository;
    private final OpenAiClient openAiClient;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @Value("${openai-service.assistants.organize}")
    private String organizeAssistants;

    @Value("${openai-service.assistants.exam}")
    private String examAssistants;

    public OrganizerService(OrganizerRepository organizerRepository, OpenAiClient openAiClient) {
        this.organizerRepository = organizerRepository;
        this.openAiClient = openAiClient;
    }

    public String organize(OrganizerRequestDto organizerRequestDto) {
        String lectureContent = audioToText(organizerRequestDto.lectureAudioFile());

        String lectureSummary = askToGpt(organizeAssistants, lectureContent);

        return lectureSummary;
    }

    private String audioToText(MultipartFile audioFile) {
        WhisperResponseDto whisperResponseDto = openAiClient.createTranscription(audioFile, "whisper-1");

        return whisperResponseDto.text();
    }

    private String askToGpt(String assistantsId, String fullText) {
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
