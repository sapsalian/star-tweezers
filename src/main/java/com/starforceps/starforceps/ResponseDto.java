package com.starforceps.starforceps;

public record ResponseDto<T>(
        int code,

        String statusMsg,

        T data
) {
}
