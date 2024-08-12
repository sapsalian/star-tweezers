package com.starforceps.starforceps.global.openfeign.dto;

import java.util.List;

public record ThreadRunReqDto(
        String assistant_id,
        boolean stream,
        ThreadVo thread
) {
    public static ThreadRunReqDto of(String assistantId, String role, String content) {
        MessageVo messageVo = new MessageVo(
                role,
                content
        );

        ThreadVo threadVo = new ThreadVo(
                List.of(messageVo)
        );

        return new ThreadRunReqDto(assistantId, false, threadVo);
    }
}

record MessageVo(
        String role,
        String content
) {
}

record ThreadVo(
        List<MessageVo> messages
) {
}


