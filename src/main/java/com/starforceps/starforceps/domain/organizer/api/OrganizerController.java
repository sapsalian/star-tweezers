package com.starforceps.starforceps.domain.organizer.api;

import com.starforceps.starforceps.domain.organizer.application.OrganizerService;
import com.starforceps.starforceps.domain.organizer.dto.OrganizerRequestDto;
import com.starforceps.starforceps.domain.organizer.dto.OrganizerResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class OrganizerController {
    private final OrganizerService organizerService;

    public OrganizerController(OrganizerService organizerService) {
        this.organizerService = organizerService;
    }

    @PostMapping(value = "/api/organizers", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public OrganizerResponseDto organize(@ModelAttribute OrganizerRequestDto organizerRequestDto) {
        Long userId = (long)1;
        return organizerService.organize(userId ,organizerRequestDto);
    }

    @GetMapping(value = "/api/organizers")
    public List<OrganizerResponseDto> getOrganizers() {
        Long userId = (long)1;
        return organizerService.getOrganizes(userId);
    }
}
