package com.starforceps.starforceps.domain.organizer.application;

import com.starforceps.starforceps.domain.organizer.dao.OrganizerRepository;
import com.starforceps.starforceps.domain.organizer.domain.Organizer;
import com.starforceps.starforceps.domain.organizer.dto.OrganizerRequestDto;
import com.starforceps.starforceps.domain.organizer.dto.OrganizerResponseDto;
import com.starforceps.starforceps.domain.organizer.dto.SimpleOrganizerResDto;
import com.starforceps.starforceps.domain.user.application.UserService;
import com.starforceps.starforceps.domain.user.dao.UserRepository;
import com.starforceps.starforceps.domain.user.domain.User;
import com.starforceps.starforceps.global.common.exception.custom_exception.NoSuchResourceException;
import com.starforceps.starforceps.global.common.exception.custom_exception.PermissionException;
import com.starforceps.starforceps.global.openfeign.util.OpenAiProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class OrganizerService {
    private final OrganizerRepository organizerRepository;
    private final OpenAiProvider openAiProvider;
    private final UserRepository userRepository;


    @Value("${openai-service.assistants.organize}")
    private String organizeAssistants;

    public OrganizerResponseDto organize(Long userId, OrganizerRequestDto organizerRequestDto) {
        String lectureContent = openAiProvider.audioToText(organizerRequestDto.lectureAudioFile());

        String lectureSummary = openAiProvider.askToGpt(organizeAssistants, lectureContent);

        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchResourceException("존재하지 않는 사용자의 요청입니다."));

        Organizer organizer = new Organizer(
                lectureContent,
                lectureSummary,
                user
        );

        organizer = organizerRepository.save(organizer);

        return OrganizerResponseDto.from(organizer);
    }

    public List<SimpleOrganizerResDto> getOrganizers(Long userId) {
        List<Organizer> organizers = organizerRepository.findAllByUserId(userId);

        return organizers.stream().map(SimpleOrganizerResDto::from).toList();
    }

    public void deleteOrganizer(Long userId, Long organizerId) {
        Organizer organizer = organizerRepository.findById(organizerId)
                        .orElseThrow(() -> new NoSuchResourceException("존재하지 않는 정리내역에 대한 삭제요청입니다."));

        Long ownerId = organizer.getUser().getId();
        if (!ownerId.equals(userId)) {
            throw new PermissionException("정리내역의 소유자가 아닙니다.");
        }

        organizerRepository.deleteById(organizerId);
    }

    public SimpleOrganizerResDto updateTitle(Long userId, Long organizerId, String title) {
        Organizer organizer = organizerRepository.findById(organizerId)
                .orElseThrow(() -> new NoSuchResourceException("존재하지 않는 정리내역에 대한 수정 요청입니다."));

        Long ownerId = organizer.getUser().getId();
        if (!ownerId.equals(userId)) {
            throw new PermissionException("정리내역의 소유자가 아닙니다.");
        }

        organizer.setTitle(title);
        organizer = organizerRepository.save(organizer);

        return SimpleOrganizerResDto.from(organizer);
    }

    public SimpleOrganizerResDto updateDescription(Long userId, Long organizerId, String description) {
        Organizer organizer = organizerRepository.findById(organizerId)
                .orElseThrow(() -> new NoSuchResourceException("존재하지 않는 정리내역에 대한 수정 요청입니다."));

        Long ownerId = organizer.getUser().getId();
        if (!ownerId.equals(userId)) {
            throw new PermissionException("정리내역의 소유자가 아닙니다.");
        }

        organizer.setDescription(description);
        organizer = organizerRepository.save(organizer);

        return SimpleOrganizerResDto.from(organizer);
    }
}
