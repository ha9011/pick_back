package com.toy.pick.api.service.collectionPlace;

import com.toy.pick.domain.collection.Collection;
import com.toy.pick.domain.collection.CollectionRepository;
import com.toy.pick.domain.collectionPlace.CollectionPlace;
import com.toy.pick.domain.collectionPlace.CollectionPlaceRepository;
import com.toy.pick.domain.place.Place;
import com.toy.pick.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class CollectionPlaceService {

    private final CollectionPlaceRepository collectionPlaceRepository;
    private final CollectionRepository collectionRepository;


    public void savedCollectionPlace(Place place, Long collectionId){
        Collection collection = collectionRepository.findById(collectionId).orElseThrow(
                () -> new CustomException("존재하지 않은 콜렉션입니다.")
        );

        CollectionPlace collectionPlace = CollectionPlace.create(collection, place);
        collectionPlaceRepository.save(collectionPlace);
    }


}
