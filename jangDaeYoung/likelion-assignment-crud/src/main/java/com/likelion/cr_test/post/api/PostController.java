package com.likelion.cr_test.post.api;



import com.likelion.cr_test.common.error.SuccessCode;
import com.likelion.cr_test.common.template.ApiResTemplate;
import com.likelion.cr_test.post.api.dto.request.PostSaveRequestDto;
import com.likelion.cr_test.post.api.dto.request.PostUpdateRequestDto;
import com.likelion.cr_test.post.api.dto.response.PostInfoResponseDto;
import com.likelion.cr_test.post.api.dto.response.PostListResponseDto;
import com.likelion.cr_test.post.application.PostService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    // 게시물 저장
    @PostMapping("/save")
    public ApiResTemplate<String> postSave(@RequestBody @Valid PostSaveRequestDto postSaveRequestDto) {
        postService.postSave(postSaveRequestDto);
        return ApiResTemplate.successWithNoContent(SuccessCode.POST_SAVE_SUCCESS);
    }

    // 사용자 id를 기준으로 해당 사용자가 작성한 게시글 목록 조회
    @GetMapping("/{memberId}")
    public ApiResTemplate<PostListResponseDto> postFindByMemberId(@PathVariable("memberId") Long memberId) {
        PostListResponseDto postListResponseDto = postService.postFindByMember(memberId);
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, postListResponseDto);
    }



    // 게시글 id를 기준으로 게시글 조회
    @GetMapping("/postId/{postId}")
    public ApiResTemplate<PostInfoResponseDto> postFindByPostId(@PathVariable("postId") Long postId) {
        PostInfoResponseDto posInfoResponseDto = postService.postFinById(postId);
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, posInfoResponseDto);
    }



    // 게시물 id를 기준으로 사용자가 작성한 게시물 수정
    @PatchMapping("/{postId}")
    public ApiResTemplate<String> postUpdate(@PathVariable("postId") Long postId,
                                             @RequestBody PostUpdateRequestDto postUpdateRequestDto) {
        postService.postUpdate(postId, postUpdateRequestDto);
        return ApiResTemplate.successWithNoContent(SuccessCode.POST_UPDATE_SUCCESS);
    }

    // 게시물 id를 기준으로 사용자가 작성한 게시물 삭제
    @DeleteMapping("/{postId}")
    public ApiResTemplate<String> postDelete(@PathVariable("postId") Long postId) {
        postService.postDelete(postId);
        return ApiResTemplate.successWithNoContent(SuccessCode.POST_DELETE_SUCCESS);
    }
}