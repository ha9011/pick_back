package com.toy.pick.domain.placeImage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceImageRepository extends JpaRepository<PlaceImage, Long> {
}
