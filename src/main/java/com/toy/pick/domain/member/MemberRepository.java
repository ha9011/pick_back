package com.toy.pick.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
     Member findByUserId(String userId);
     Member findByAccessToken(String accessToken);


     @Query("SELECT m FROM Member m JOIN FETCH m.memberCollections mc JOIN FETCH mc.collection c WHERE m.id = :memberId")
     Member findByMemberWithFollowCollection(@Param("memberId") Long id);


}
