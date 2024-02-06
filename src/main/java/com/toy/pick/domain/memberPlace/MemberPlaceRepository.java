package com.toy.pick.domain.memberPlace;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberPlaceRepository extends JpaRepository<MemberPlace, Long> {
}
