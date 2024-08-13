package com.starforceps.starforceps.global.common.exception.custom_exception;

public class PermissionException extends RuntimeException {
    public PermissionException() {
        super("해당 자원에 대한 접근 권한이 없습니다.");
    }

    public PermissionException(String message) {
        super(message);
    }
}
