package com.starforceps.starforceps.domain.organizer.application;

import com.starforceps.starforceps.domain.organizer.dao.OrganizerRepository;
import com.starforceps.starforceps.domain.organizer.domain.Organizer;
import com.starforceps.starforceps.domain.organizer.dto.OrganizerRequestDto;
import com.starforceps.starforceps.domain.organizer.dto.OrganizerResponseDto;
import com.starforceps.starforceps.domain.user.domain.User;
import com.starforceps.starforceps.global.openfeign.util.OpenAiProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrganizerService {
    private final OrganizerRepository organizerRepository;
    private final OpenAiProvider openAiProvider;

    @Value("${openai-service.assistants.organize}")
    private String organizeAssistants;

    public OrganizerService(OrganizerRepository organizerRepository, OpenAiProvider openAiProvider) {
        this.organizerRepository = organizerRepository;
        this.openAiProvider = openAiProvider;
    }

    public OrganizerResponseDto organize(Long userId, OrganizerRequestDto organizerRequestDto) {
        String lectureContent = openAiProvider.audioToText(organizerRequestDto.lectureAudioFile());

        String lectureSummary = openAiProvider.askToGpt(organizeAssistants, lectureContent);

        User user = new User();

        Organizer organizer = new Organizer(
                lectureContent,
                lectureSummary,
                user
        );

        organizer = organizerRepository.save(organizer);

        return OrganizerResponseDto.from(organizer);
    }

}
