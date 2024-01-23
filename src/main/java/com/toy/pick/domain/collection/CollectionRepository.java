package com.toy.pick.domain.collection;

import com.toy.pick.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {
    List<Collection> findAllByMember(Member member);
}
