package com.likelion.cr_test.tag.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record TagUpdateRequestDto(
        @NotBlank(message = "태그를 입력하셔야 합니다")
        String tagName
) {
}
