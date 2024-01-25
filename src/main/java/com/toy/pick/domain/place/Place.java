package com.toy.pick.domain.place;

import com.toy.pick.domain.BaseEntity;
import com.toy.pick.domain.common.ItemStatus;
import com.toy.pick.domain.placeImage.PlaceImage;
import jakarta.persistence.*;
import lombok.AccessLevel;
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
    private PlaceCategory category;

    // 이미지
    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name ="place_id")
    private List<PlaceImage> placeImages;

}
