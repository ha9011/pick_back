package com.toy.pick.domain.memberPlace;

import com.toy.pick.domain.member.Member;
import com.toy.pick.domain.place.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberPlaceRepository extends JpaRepository<MemberPlace, Long> {

    Optional<MemberPlace> findByMemberIdAndPlaceId(Long memberId, Long id);
    boolean existsByMemberAndPlace(Member member, Place place);


}
