package com.toy.pick.api.controller.collectionPlace.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "컬렉션 수정 RequestDto")
public class RemovePlaceInMyCollectionsByCIdReq {
    @Schema(description = "컬랙션에 포함된 장소들 중 삭제 리스트")
    private List<Long> removePlaceIds = new ArrayList<>();
}
