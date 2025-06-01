package com.likelion.cr_test.member.api.dto.request;

import com.likelion.cr_test.member.domain.Part;

public record MemberSaveRequestDto(
        String name,
        int age,
        Part part
) {

}
