package com.starforceps.starforceps.domain.organizer.application;

import com.starforceps.starforceps.domain.organizer.dao.OrganizerRepository;
import com.starforceps.starforceps.domain.organizer.dto.OrganizerRequestDto;
import com.starforceps.starforceps.global.openfeign.dto.WhisperRequestDto;
import com.starforceps.starforceps.global.openfeign.dto.WhisperResponseDto;
import com.starforceps.starforceps.global.openfeign.feign_client.OpenAiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class OrganizerService {
    private final OrganizerRepository organizerRepository;
    private final OpenAiClient openAiClient;

    public OrganizerService(OrganizerRepository organizerRepository, OpenAiClient openAiClient) {
        this.organizerRepository = organizerRepository;
        this.openAiClient = openAiClient;
    }

    public String organize(OrganizerRequestDto organizerRequestDto) {
        String lectureContent = audioToText(organizerRequestDto.lectureAudioFile());

        return lectureContent;
    }

    public String audioToText(MultipartFile audioFile) {
        WhisperRequestDto whisperRequestDto = new WhisperRequestDto(
                audioFile,
                "whisper-1"
        );

        WhisperResponseDto whisperResponseDto = openAiClient.createTranscription(whisperRequestDto);

        return whisperResponseDto.text();
    }
}
