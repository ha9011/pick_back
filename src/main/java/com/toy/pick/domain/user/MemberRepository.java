package com.toy.pick.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
     Member findByUserId(String userId);
     Member findByAccessToken(String accessToken);
}
