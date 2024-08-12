package com.starforceps.starforceps.global.openfeign.dto;

import java.util.List;

public record MessagesResDto(
    List<DataVo> data
) {
    public String getReply() {
        return this.data().get(0).content().get(0).text().value();
    }
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
