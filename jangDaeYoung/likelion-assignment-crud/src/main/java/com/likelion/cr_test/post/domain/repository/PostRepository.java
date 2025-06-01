package com.likelion.cr_test.post.domain.repository;

import com.likelion.cr_test.member.domain.Member;
import com.likelion.cr_test.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByMember(Member member);
}
