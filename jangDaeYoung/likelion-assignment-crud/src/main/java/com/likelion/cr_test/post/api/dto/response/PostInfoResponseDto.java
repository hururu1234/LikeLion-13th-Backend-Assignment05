package com.likelion.cr_test.post.api.dto.response;

import com.likelion.cr_test.post.domain.Post;
import com.likelion.cr_test.postTag.domain.PostTag;
import com.likelion.cr_test.tag.domain.Tag;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;


@Builder
public record PostInfoResponseDto(
        String title,
        String contents,
        String writer,

        List<String>tags

) {
    public static PostInfoResponseDto from(Post post) {

        List<String> tags = new ArrayList<>();

        //최대한 이해하기 위해서 스트림 안쓰고 반복문 사용했습니다...
        //태그값 배열 찾아서 응답에 넣는 부분
        for (PostTag postTag : post.getPostTags()) {
            Tag tag = postTag.getTag();
            String tagName = tag.getName();
            tags.add(tagName);
        }


        return PostInfoResponseDto.builder()
                .title(post.getTitle())
                .contents(post.getContents())
                .writer(post.getMember().getName())
                .tags(tags)
                .build();
    }
}