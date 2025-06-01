package com.likelion.cr_test.tag.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TagSaveRequestDto(

        @NotBlank(message = "태그를 입력하셔야 합니다")
        @Size(min = 2, max = 10)
        String tagName

) {
}
