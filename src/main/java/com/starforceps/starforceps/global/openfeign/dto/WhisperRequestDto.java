package com.starforceps.starforceps.global.openfeign.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

public record WhisperRequestDto (
        MultipartFile file,
        String model
)  implements Serializable {
    
}
