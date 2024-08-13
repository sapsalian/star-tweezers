package com.starforceps.starforceps.domain.organizer.api;

import com.starforceps.starforceps.domain.organizer.application.OrganizerService;
import com.starforceps.starforceps.domain.organizer.dto.OrganizerRequestDto;
import com.starforceps.starforceps.domain.organizer.dto.OrganizerResponseDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
        return organizerService.getOrganizers(userId);
    }

    @DeleteMapping(value = "/api/organizers/{organizer_id}")
    public void deleteOrganizer(@PathVariable Long organizer_id) {
        Long userId = (long)1;
        organizerService.deleteOrganizer(userId, organizer_id);
    }
}
