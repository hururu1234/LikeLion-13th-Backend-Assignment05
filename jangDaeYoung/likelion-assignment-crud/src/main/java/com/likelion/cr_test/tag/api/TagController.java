package com.likelion.cr_test.tag.api;


import com.likelion.cr_test.common.error.SuccessCode;
import com.likelion.cr_test.common.template.ApiResTemplate;
import com.likelion.cr_test.member.api.dto.request.MemberUpdateRequestDto;
import com.likelion.cr_test.member.api.dto.response.MemberListResponseDto;
import com.likelion.cr_test.post.api.dto.request.PostSaveRequestDto;
import com.likelion.cr_test.post.api.dto.response.PostListResponseDto;
import com.likelion.cr_test.tag.api.dto.request.TagSaveRequestDto;
import com.likelion.cr_test.tag.api.dto.request.TagUpdateRequestDto;
import com.likelion.cr_test.tag.api.dto.response.TagInfoResponseDto;
import com.likelion.cr_test.tag.api.dto.response.TagListResponseDto;
import com.likelion.cr_test.tag.application.TagService;
import com.likelion.cr_test.tag.domain.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tag")
public class TagController {

    private final TagService tagService;

    //태그 저장
    @PostMapping("/save")
    public ApiResTemplate<String> tagSave(@RequestBody @Valid TagSaveRequestDto tagSaveRequestDto) {
        tagService.tagSave(tagSaveRequestDto);
        return ApiResTemplate.successWithNoContent(SuccessCode.TAG_SAVE_SUCCESS);
    }

    //태그 id로 조회
    @GetMapping("/{tagId}")
    public ApiResTemplate<TagInfoResponseDto> tagFindById(@PathVariable("tagId") Long tagId) {
        TagInfoResponseDto tagInfoResponseDto = tagService.findTagById(tagId);
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, tagInfoResponseDto);
    }

    //태그 전체 조회
    @GetMapping("/all")
    public ApiResTemplate<TagListResponseDto> tagFindAll() {
        TagListResponseDto tagListResponseDto = tagService.findTagAll();
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, tagListResponseDto);
    }

    // 태그 id를 통한 사용자 수정
    @PatchMapping("/{tagId}")
    public ApiResTemplate<String> tagUpdate(@PathVariable("tagId") Long tagId,
                                               @RequestBody TagUpdateRequestDto tagUpdateRequestDto) {
        tagService.tagUpdate(tagId, tagUpdateRequestDto);
        return ApiResTemplate.successWithNoContent(SuccessCode.TAG_UPDATE_SUCCESS);
    }

    // 태그 id를 통한 사용자 삭제
    @DeleteMapping("/{tagId}")
    public ApiResTemplate<String> tagDelete(@PathVariable("tagId") Long tagId) {
        tagService.tagDelete(tagId);
        return ApiResTemplate.successWithNoContent(SuccessCode.TAG_DELETE_SUCCESS);
    }


}
