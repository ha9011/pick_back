package com.toy.pick.domain.collection;

import com.toy.pick.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {


    // TODO -> 장소 메모가 개별인지 공통인지에 따라 다시 변경;
    // -> 장소, 메모 마다 collection updateAt을 업데이트 하자.
    @Query("select c from Collection c where c.member = :member ")
    List<Collection> findAllByMember(@Param("member") Member member);
}
