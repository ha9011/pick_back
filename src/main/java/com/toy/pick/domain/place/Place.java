package com.toy.pick.domain.place;

import com.toy.pick.api.controller.place.request.PostCollectionPlaceReq;
import com.toy.pick.domain.BaseEntity;
import com.toy.pick.domain.common.ItemStatus;
import com.toy.pick.domain.placeImage.PlaceImage;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Place extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 장소이름
    private String name;
    // 주소
    private String address;
    // 상세주소
    private String detailAddress;

    // x
    private String x;

    // y
    private String y;

    // 장소 카테고리
    @Enumerated(EnumType.STRING)
    private PlaceCategory category;

    // 이미지
    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name ="place_id")
    private List<PlaceImage> placeImages;


    @Builder
    public Place(Long id, String name, String address, String detailAddress, String x, String y, PlaceCategory category, ItemStatus itemStatus) {
        this.name = name;
        this.address = address;
        this.detailAddress = detailAddress;
        this.x = x;
        this.y = y;
        this.category = category;
        this.itemStatus = itemStatus;
    }

    public static Place create(PostCollectionPlaceReq req){
        return Place.builder()
                .address(req.getAddress())
                .category(PlaceCategory.valueOf(req.getCategory()))
                .name(req.getName())
                .x(req.getX())
                .y(req.getY())
                .itemStatus(ItemStatus.valueOf(req.getItemStatus()))
                .detailAddress(req.getDetailAddress())
                .build();
    }

    public void updatePlaceImg(List<PlaceImage> placeImages){
            this.placeImages = placeImages;
    }

}
