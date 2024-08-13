package com.starforceps.starforceps.global.common.exception.handler;

import com.starforceps.starforceps.global.common.dto.ResponseDto;
import com.starforceps.starforceps.global.common.exception.custom_exception.NoSuchResourceException;
import com.starforceps.starforceps.global.common.exception.custom_exception.PermissionException;
import jakarta.validation.constraints.Null;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoSuchResourceException.class)
    public ResponseEntity<ResponseDto<Null>> handleNoSuchResourceException(Exception e) {
        ResponseDto<Null> responseDto = new ResponseDto<>(
                4040,
                e.getMessage(),
                null
        );

        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PermissionException.class)
    public ResponseEntity<ResponseDto<Null>> handlePermissionException(PermissionException e) {
        ResponseDto<Null> responseDto = new ResponseDto<>(
                4030,
                e.getMessage(),
                null
        );

        return new ResponseEntity<>(responseDto, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto<Null>> handleException(Exception e) {
        ResponseDto<Null> responseDto = new ResponseDto<>(
                5000,
                e.getMessage(),
                null
        );

        return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
