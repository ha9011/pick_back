package com.toy.pick.domain.collection;

import com.toy.pick.api.service.collection.response.MyCollectionsRes;
import com.toy.pick.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {


    // TODO -> 장소 메모가 개별인지 공통인지에 따라 다시 변경; -> 그냥 컬렉션별 개별로
    // -> 장소, 메모 마다 collection updateAt을 업데이트 하자.
    @Query("select new com.toy.pick.api.service.collection.response.MyCollectionsRes(c, count(cp.place)) from Collection c JOIN FETCH c.member m LEFT JOIN c.collectionPlaces cp" +
            " group by c.id" +
            " having c.member = :member ")
    List<MyCollectionsRes> findAllByMember(@Param("member") Member member);

    @Query("select c from Collection c LEFT JOIN FETCH c.collectionPlaces cp LEFT JOIN FETCH cp.place p where c.id = :cId ")
    Collection collectionWithPlaceByCId(@Param("cId") Long cId);





}
