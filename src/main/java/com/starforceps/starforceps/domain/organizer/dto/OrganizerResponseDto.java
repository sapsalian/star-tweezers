package com.starforceps.starforceps.domain.organizer.dto;

import java.time.LocalDate;

public record OrganizerResponseDto(
    Long id,
    String title,
    String description,
    LocalDate createdAt,
    String mainPoint,
    String summary,
    String examPoint
) {
}
