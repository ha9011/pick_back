package com.toy.pick.domain.collectionPlace;

import com.toy.pick.domain.collection.Collection;
import com.toy.pick.domain.collection.CollectionRepository;
import com.toy.pick.domain.member.Member;
import com.toy.pick.domain.place.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CollectionPlaceRepository extends JpaRepository<CollectionPlace, Long> {

    @Query("delete from CollectionPlace cp where cp.collection = :collection and cp.place = :places")
    void removePlacesInCollectionByCid(@Param("collection") Collection collection, @Param("places") List<Place> places);
}
