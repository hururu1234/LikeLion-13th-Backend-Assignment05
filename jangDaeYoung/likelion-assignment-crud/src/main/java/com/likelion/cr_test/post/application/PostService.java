package com.likelion.cr_test.post.application;

import com.likelion.cr_test.common.error.ErrorCode;
import com.likelion.cr_test.common.exception.BusinessException;
import com.likelion.cr_test.member.domain.Member;
import com.likelion.cr_test.member.domain.repository.MemberRepository;
import com.likelion.cr_test.post.api.dto.request.PostSaveRequestDto;
import com.likelion.cr_test.post.api.dto.request.PostUpdateRequestDto;
import com.likelion.cr_test.post.api.dto.response.PostInfoResponseDto;
import com.likelion.cr_test.post.api.dto.response.PostListResponseDto;
import com.likelion.cr_test.post.domain.Post;
import com.likelion.cr_test.post.domain.repository.PostRepository;
import com.likelion.cr_test.postTag.domain.PostTag;
import com.likelion.cr_test.tag.api.dto.response.TagInfoResponseDto;
import com.likelion.cr_test.tag.domain.Tag;
import com.likelion.cr_test.tag.domain.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final TagRepository tagRepository;

    // 게시물 저장
    @Transactional
    public void postSave(PostSaveRequestDto postSaveRequestDto) {
        Member member = memberRepository.findById(postSaveRequestDto.memberId()).orElseThrow(IllegalArgumentException::new);

        Post post = Post.builder()
                .title(postSaveRequestDto.title())
                .contents(postSaveRequestDto.contents())
                .member(member)
                .build();

        List<String> tagNames = postSaveRequestDto.tags();


        //최대한 이해하기 위해서 스트림 안쓰고 반복문 사용했습니다.
        for (String tagName : tagNames) {
            // 기존에 태그가 있는지 확인
            Optional<Tag> optionalTag = tagRepository.findByName(tagName);

            Tag tag;
            if (optionalTag.isPresent()) {
                tag = optionalTag.get();
            } else {
                // 없으면 새로 생성해서 저장
                tag = tagRepository.save(new Tag(tagName));
            }

            // PostTag 객체 생성 후 추가
            PostTag postTag = new PostTag(post, tag);
            post.getPostTags().add(postTag);
        }

        postRepository.save(post);



    }

    // 특정 작성자가 작성한 게시글 목록을 조회
    public PostListResponseDto postFindByMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(IllegalArgumentException::new);

        List<Post> posts = postRepository.findByMember(member);
        List<PostInfoResponseDto> postInfoResponseDtos = posts.stream()
                .map(PostInfoResponseDto::from)
                .toList();

        return PostListResponseDto.from(postInfoResponseDtos);
    }


    //게시글 id로 게시글 조회
    public PostInfoResponseDto postFinById(Long postId){
       Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BusinessException(ErrorCode.POST_NOT_FOUND_EXCEPTION, ErrorCode.POST_NOT_FOUND_EXCEPTION.getMessage() + postId));
        return PostInfoResponseDto.from(post);
    }




    @Transactional
    public void postUpdate(Long postId, PostUpdateRequestDto postUpdateRequestDto){
        Post post = postRepository.findById(postId)
                .orElseThrow(IllegalArgumentException::new);

        post.update(postUpdateRequestDto);
    }

    @Transactional
    public void postDelete(Long postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(IllegalArgumentException::new);
        
        postRepository.delete(post);
    }
}