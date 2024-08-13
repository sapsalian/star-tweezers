package com.starforceps.starforceps.domain.organizer.dto;

import com.starforceps.starforceps.domain.organizer.domain.Organizer;

import java.time.LocalDate;

public record OrganizerResponseDto(
    Long id,
    String title,
    String description,
    LocalDate createdAt,
    String originalText,
    String organizedText
) {
    public static OrganizerResponseDto from(Organizer organizer) {
        return new OrganizerResponseDto(
                organizer.getId(),
                organizer.getTitle(),
                organizer.getDescription(),
                organizer.getCreatedAt().toLocalDate(),
                organizer.getOriginalText(),
                organizer.getOrganizedText()
        );
    }
}
