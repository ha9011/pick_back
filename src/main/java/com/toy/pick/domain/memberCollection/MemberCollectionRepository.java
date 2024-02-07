package com.toy.pick.domain.memberCollection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberCollectionRepository extends JpaRepository<MemberCollection, Long> {
}
