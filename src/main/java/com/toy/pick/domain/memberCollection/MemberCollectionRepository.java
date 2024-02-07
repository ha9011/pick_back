package com.toy.pick.domain.memberCollection;

import com.toy.pick.domain.collection.Collection;
import com.toy.pick.domain.member.MemberRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberCollectionRepository extends JpaRepository<MemberCollection, Long> {


    Optional<MemberCollection> findByMemberIdAndCollectionId(Long memberId, Long collectionId);
    List<MemberCollection> findByMemberId(Long memberId);
}
