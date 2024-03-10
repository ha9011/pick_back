package com.toy.pick.domain.place;

import com.toy.pick.domain.collection.Collection;
import com.toy.pick.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    @Query("select m from Member m " +
            "INNER JOIN FETCH m.memberPlaces mp " +
            "INNER JOIN FETCH mp.place " +
            "WHERE m.id = :mId AND mp.place.id = :pId"
    )
    Member test(@Param("mId") Long mId, @Param("pId") Long pId);
}
