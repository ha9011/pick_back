package com.toy.pick.api.service.sample.response;

import com.toy.pick.domain.sample.Sample;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

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
