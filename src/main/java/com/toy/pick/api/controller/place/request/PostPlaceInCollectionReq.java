package com.toy.pick.api.controller.place.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Schema(description = "추가된 장소를 컬렉션에 격납하기")
@Getter
@ToString
public class PostPlaceInCollectionReq {

    @Schema(description = "장소를 추가할 컬렉션 ID")
    private final Long collectionId;

    @Schema(description = "장소 ID")
    private final Long placeId;

    // TODO 변경될 가능성 있음
    @Schema(description = "나의 장소 메모")
    private final String memo;

    @Schema(description = "나의 장소 URL")
    private final String url;


    public PostPlaceInCollectionReq(Long collectionId, Long placeId, String memo, String url) {
        this.collectionId = collectionId;
        this.placeId = placeId;
        this.memo = memo;
        this.url = url;
    }
}
