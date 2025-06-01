package com.likelion.cr_test.tag.api.dto.response;

import com.likelion.cr_test.tag.domain.Tag;
import lombok.Builder;

@Builder
public record TagInfoResponseDto(
        String name
) {

    public static TagInfoResponseDto from(Tag tag){
        return TagInfoResponseDto.builder()
                .name(tag.getName())
                .build();
    }
}
