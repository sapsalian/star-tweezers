package com.starforceps.starforceps.global.openfeign.dto;

import java.util.List;

public record MessagesResDto(
    List<DataVo> data
) {
}

record DataVo(
        List<ContentVo> content
) {
}

record ContentVo(
   TextVo text
) {
}

record TextVo(
   String value
) {
}
