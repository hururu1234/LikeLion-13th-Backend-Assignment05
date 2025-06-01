package com.likelion.cr_test.post.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.likelion.cr_test.member.domain.Member;
import com.likelion.cr_test.post.api.dto.request.PostUpdateRequestDto;
import com.likelion.cr_test.postTag.domain.PostTag;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id",nullable = false )
    private Member member;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostTag> postTags = new ArrayList<>();

    @Builder
    private Post(String title, String contents, Member member) {
        this.title = title;
        this.contents = contents;
        this.member = member;

    }

    public void update(PostUpdateRequestDto postUpdateRequestDto){
        this.title = postUpdateRequestDto.title();
        this.contents = postUpdateRequestDto.content();
    }




}
