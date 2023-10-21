package com.toy.pick.api.controller.sample.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GetSampleReqDto {
    // @NotEmpty
    String name;
}
