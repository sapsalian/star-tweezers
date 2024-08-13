package com.starforceps.starforceps.domain.organizer.api;

import com.starforceps.starforceps.domain.organizer.application.OrganizerService;
import com.starforceps.starforceps.domain.organizer.dto.OrganizerRequestDto;
import com.starforceps.starforceps.domain.organizer.dto.OrganizerResponseDto;
import com.starforceps.starforceps.domain.organizer.dto.SimpleOrganizerResDto;
import com.starforceps.starforceps.global.common.custom_annotation.annotation.TokenId;
import com.starforceps.starforceps.global.common.dto.ResponseDto;
import jakarta.validation.constraints.Null;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrganizerController {
    private final OrganizerService organizerService;

    public OrganizerController(OrganizerService organizerService) {
        this.organizerService = organizerService;
    }

    @PostMapping(value = "/api/organizers", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDto<OrganizerResponseDto>> organize(@ModelAttribute OrganizerRequestDto organizerRequestDto, @TokenId Long userId) {
        OrganizerResponseDto organizerResponseDto = organizerService.organize(userId ,organizerRequestDto);

        ResponseDto<OrganizerResponseDto> responseDto = new ResponseDto<>(
                2000,
                "정리 생성 완료",
                organizerResponseDto
        );

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping(value = "/api/organizers")
    public ResponseEntity<ResponseDto<List<SimpleOrganizerResDto>>> getOrganizers(@TokenId Long userId) {
        List<SimpleOrganizerResDto> organizers = organizerService.getOrganizers(userId);

        ResponseDto<List<SimpleOrganizerResDto>> responseDto = new ResponseDto<>(
                2001,
                "정리 조회 완료",
                organizers
        );

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping(value = "/api/organizers/{organizer_id}")
    public ResponseEntity<ResponseDto<Null>> deleteOrganizer(@PathVariable Long organizer_id, @TokenId Long userId) {
        organizerService.deleteOrganizer(userId, organizer_id);

        ResponseDto<Null> responseDto = new ResponseDto<>(
                2002,
                "정리 삭제 완료",
                null
        );

        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping(value = "/api/organizers/{organizer_id}/title")
    public ResponseEntity<ResponseDto<SimpleOrganizerResDto>> updateTitle(
            @RequestParam String title,
            @TokenId Long userId,
            @PathVariable Long organizer_id
    ) {
        SimpleOrganizerResDto simpleOrganizerResDto = organizerService.updateTitle(userId, organizer_id, title);

        ResponseDto<SimpleOrganizerResDto> responseDto = new ResponseDto<>(
                2003,
                "정리 제목 수정 완료",
                simpleOrganizerResDto
        );

        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping(value = "/api/organizers/{organizer_id}/description")
    public ResponseEntity<ResponseDto<SimpleOrganizerResDto>> updateDescription(
            @RequestParam String description,
            @TokenId Long userId,
            @PathVariable Long organizer_id
    ) {
        SimpleOrganizerResDto simpleOrganizerResDto = organizerService.updateDescription(userId, organizer_id, description);

        ResponseDto<SimpleOrganizerResDto> responseDto = new ResponseDto<>(
                2003,
                "정리 설명 수정 완료",
                simpleOrganizerResDto
        );

        return ResponseEntity.ok(responseDto);
    }
}
