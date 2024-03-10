package com.toy.pick.api.controller.collection.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "컬렉션 생성 RequestDto")
public class PostMyCollectionsReq {

    @Length(min = 1, max = 15, message = "최대 15자까지만 입력할 수 있습니다.")
    @NotBlank(message = "필수 값입니다.")
    @Schema(description = "컬랙션 제목")
    private String title;


    @Length(max = 50, message = "최대 50자까지만 입력할 수 있습니다.")
    @Schema(description = "컬랙션 메모")
    private String memo;
}
