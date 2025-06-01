package com.likelion.cr_test.tag.domain.repository;

import com.likelion.cr_test.tag.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {

    //태그 입력값 비교 위해서, JPA의 기본 제공 메서드에는 findByName이 없다.
    //optional로 null 방지
    Optional<Tag> findByName(String name);


}