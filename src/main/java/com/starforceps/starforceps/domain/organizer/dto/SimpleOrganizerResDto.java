package com.starforceps.starforceps.domain.organizer.dto;

import com.starforceps.starforceps.domain.organizer.domain.Organizer;

public record SimpleOrganizerResDto(
        Long id,
        String title,
        String description
) {
    public static SimpleOrganizerResDto from(Organizer organizer) {
        return new SimpleOrganizerResDto(
                organizer.getId(),
                organizer.getTitle(),
                organizer.getDescription()
        );
    }
}
