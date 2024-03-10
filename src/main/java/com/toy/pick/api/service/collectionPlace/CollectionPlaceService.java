package com.toy.pick.api.service.collectionPlace;

import com.toy.pick.api.controller.collection.request.PutMyCollectionsReq;
import com.toy.pick.api.controller.collectionPlace.request.RemovePlaceInMyCollectionsByCIdReq;
import com.toy.pick.domain.collection.Collection;
import com.toy.pick.domain.collection.CollectionRepository;
import com.toy.pick.domain.collectionPlace.CollectionPlace;
import com.toy.pick.domain.collectionPlace.CollectionPlaceRepository;
import com.toy.pick.domain.place.Place;
import com.toy.pick.domain.place.PlaceRepository;
import com.toy.pick.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class CollectionPlaceService {

    private final CollectionPlaceRepository collectionPlaceRepository;
    private final CollectionRepository collectionRepository;
    private final PlaceRepository placeRepository;


    public void savedCollectionPlace(Place place, Long collectionId, String memo, String url){
        Collection collection = collectionRepository.findById(collectionId).orElseThrow(
                () -> new CustomException("존재하지 않은 콜렉션입니다.")
        );

        CollectionPlace collectionPlace = CollectionPlace.create(collection, place, memo, url);
        collectionPlaceRepository.save(collectionPlace);
    }

    @Transactional
    public void removePlaceInCollection(Long cId, RemovePlaceInMyCollectionsByCIdReq req) {
        Collection collection = collectionRepository.findById(cId).orElseThrow(
                () -> new CustomException("해당 컬렉션은 존재하지 않습니다.")
        );

        // 삭제할 Plcce Entity 가져오기
        List<Place> places = placeRepository.findAllById(req.getRemovePlaceIds());
        if(places.size() != req.getRemovePlaceIds().size()){
            throw new CustomException("삭제할 장소가 존재하지 않습니다.");
        }
        // 장소 삭제
        collectionPlaceRepository.removePlacesInCollectionByCid(collection, places);


        // 해당 콜렉션 최근업데이트 새로 갱신
        collection.refreshLastUpdateAt(LocalDateTime.now());

    }


}
