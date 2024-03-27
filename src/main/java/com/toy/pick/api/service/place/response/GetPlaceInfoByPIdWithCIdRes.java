package com.toy.pick.api.service.place.response;

import com.toy.pick.api.service.collection.response.MyCollectionsRes;
import com.toy.pick.domain.collection.Collection;
import com.toy.pick.domain.collectionPlace.CollectionPlace;
import com.toy.pick.domain.place.Place;
import com.toy.pick.domain.place.PlaceCategory;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class GetPlaceInfoByPIdWithCIdRes {
    private Long id;
    // 장소이름
    private String name;
    // 주소
    private String address; // 도로명
    // 상세주소
    private String detailAddress;

    // x
    private String x;

    // y
    private String y;

    // 장소 카테고리
    private PlaceCategory category;

    private String memo;
    private String url;

    private LocalDateTime approach50mAt;

    private List<CollectionInfo> collectionInfos;

    public GetPlaceInfoByPIdWithCIdRes(Place p, CollectionPlace cp, LocalDateTime approach50mAt, List<MyCollectionsRes> collectionInfos) {
        this.id = p.getId();
        this.name = p.getName();
        this.address = p.getAddress();
        this.detailAddress = p.getDetailAddress();
        this.x = p.getX();
        this.y = p.getY();
        this.category = p.getCategory();
        this.memo = cp.getMemo();
        this.url = cp.getUrl();
        this.approach50mAt = approach50mAt;
        this.collectionInfos = collectionInfos.stream().map(c -> CollectionInfo.of(c) ).collect(Collectors.toList());
    }

    public static GetPlaceInfoByPIdWithCIdRes of(Place p, CollectionPlace cp, LocalDateTime approach50mAt, List<MyCollectionsRes> collectionInfos){
        return new GetPlaceInfoByPIdWithCIdRes(p,  cp, approach50mAt, collectionInfos);
    }

    @Getter
    static class CollectionInfo {
        private String title;
        private Long placeCount;
        private String lastUpdateAt;


        public CollectionInfo(MyCollectionsRes c) {
            this.title = c.getTitle();
            this.lastUpdateAt = c.getLastUpdateTime();
            this.placeCount = c.getPlaceCount();
        }

        public static CollectionInfo of(MyCollectionsRes c){
            return new CollectionInfo(c);
        }

    }


}
