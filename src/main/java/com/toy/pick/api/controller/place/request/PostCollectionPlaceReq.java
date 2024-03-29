package com.toy.pick.api.controller.place.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Schema(description = "컬렉션에 장소 추가 & 장소 등록")
@Getter
@ToString
public class PostCollectionPlaceReq {

    @Schema(description = "장소를 추가할 컬렉션 ID")
    private final Long collectionId;

    // 장소이름
    @Schema(description = "장소명")
    private final String name;

    // 주소
    @Schema(description = "주소(도로명)")
    private final String address;

    // 상세주소
    @Schema(description = "상세주소")
    private final String detailAddress;

    // x
    @Schema(description = "x")
    private final String x;

    // y
    @Schema(description = "y")
    private final String y;

    // 장소 카테고리
    @Schema(description = "장소 카테고리")
    private final String category;

    // 이미지

    @Schema(description = "이미지 공개/비공개", defaultValue = "PUBLIC", allowableValues = {"PUBLIC", "PRIVATE"})
    private final String itemStatus;

    @Schema(description = "장소 이미지(최대 6개)")
    private final List<MultipartFile> files;

    // TODO 변경될 가능성 있음
    @Schema(description = "나의 장소 메모")
    private final String memo;

    @Schema(description = "나의 장소 URL")
    private final String url;



    public PostCollectionPlaceReq(Long collectionId, String name, String address, String detailAddress, String x, String y, String category, String itemStatus, List<MultipartFile> files, String memo, String url) {
        this.collectionId = collectionId;
        this.name = name;
        this.address = address;
        this.detailAddress = detailAddress;
        this.x = x;
        this.y = y;
        this.category = category;
        this.itemStatus = itemStatus;
        this.files = files;
        this.memo = memo;
        this.url = url;
    }
}
