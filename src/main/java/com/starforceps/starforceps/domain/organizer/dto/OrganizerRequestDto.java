package com.starforceps.starforceps.domain.organizer.dto;

import org.springframework.web.multipart.MultipartFile;

public record OrganizerRequestDto(
        MultipartFile lectureAudioFile
) {
}
