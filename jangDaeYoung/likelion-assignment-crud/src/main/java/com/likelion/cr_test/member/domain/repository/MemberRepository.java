package com.likelion.cr_test.member.domain.repository;

import com.likelion.cr_test.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
