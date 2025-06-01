package com.likelion.cr_test.postTag.repository;

import com.likelion.cr_test.postTag.domain.PostTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostTagRepository extends JpaRepository<PostTag, Long> {
}