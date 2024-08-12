package com.starforceps.starforceps.global.openfeign.dto;

import org.springframework.web.multipart.MultipartFile;

public record WhisperRequestDto(
    MultipartFile multipartFile,
    String model
) {
}
