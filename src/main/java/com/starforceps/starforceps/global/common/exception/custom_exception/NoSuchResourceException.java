package com.starforceps.starforceps.global.common.exception.custom_exception;

public class NoSuchResourceException extends RuntimeException {
    public NoSuchResourceException() {
        super("존재하지 않는 자원입니다.");
    }

    public NoSuchResourceException(String message) {
        super(message);
    }
}
