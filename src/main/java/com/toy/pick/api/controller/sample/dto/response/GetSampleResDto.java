package com.toy.pick.api.controller.sample.dto.response;

import com.toy.pick.domain.sample.Sample;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GetSampleResDto {

    private List<Sample> sampleList;

    @Builder
    public GetSampleResDto(List<Sample> sampleList){
        this.sampleList = sampleList;
    }

    public static GetSampleResDto of(List<Sample> sampleList){
        return GetSampleResDto.builder()
                .sampleList(sampleList)
                .build();
    }
}
