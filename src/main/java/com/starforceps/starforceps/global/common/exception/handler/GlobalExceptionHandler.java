package com.starforceps.starforceps.global.common.exception.handler;

import com.starforceps.starforceps.global.common.dto.ResponseDto;
import jakarta.validation.constraints.Null;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto<Null>> handleException(Exception e) {
        ResponseDto<Null> responseDto = new ResponseDto<>(
                5000,
                "서버에서 알 수 없는 에러가 발생했습니다.",
                null
        );

        return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
