package com.toy.pick.api.controller.collection.request;

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
public class PutMyCollectionsReq {

    @Length(min = 1, max = 15, message = "최대 15자까지만 입력할 수 있습니다.")
    @NotBlank(message = "필수 값입니다.")
    @Schema(description = "컬랙션 제목")
    private String title;

    @Length(max = 50, message = "최대 50자까지만 입력할 수 있습니다.")
    @Schema(description = "컬랙션 메모")
    private String memo;

    @Schema(description = "컬랙션에 포함된 장소들 중 삭제 리스트")
    private List<Long> removePlaceIds = new ArrayList<>();
}
