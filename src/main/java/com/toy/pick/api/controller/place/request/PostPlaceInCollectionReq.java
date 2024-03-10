package com.toy.pick.api.controller.place.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Schema(description = "추가된 장소를 컬렉션에 격납하기")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostPlaceInCollectionReq {

    @Schema(description = "장소를 추가할 컬렉션 ID")
    private Long collectionId;

    @Schema(description = "장소 ID")
    private Long placeId;

    @Schema(description = "나의 장소 메모")
    private String memo;

    @Schema(description = "나의 장소 URL")
    private String url;


}
