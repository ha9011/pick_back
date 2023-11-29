package com.toy.pick.api.service.sample;

import com.toy.pick.api.controller.sample.SampleController;
import com.toy.pick.api.controller.sample.dto.request.GetSampleReqDto;
import com.toy.pick.api.controller.sample.dto.response.GetSampleResDto;
import com.toy.pick.domain.sample.Sample;
import com.toy.pick.domain.sample.SampleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SampleService {

    private final SampleRepository sampleRepository;
    public GetSampleResDto selectSampleByName(GetSampleReqDto getSampleReqDto) throws Exception {
        log.info("params : " + getSampleReqDto.toString());
        List<Sample> sampleList = sampleRepository.findByName(getSampleReqDto.getName());

        return GetSampleResDto.of(sampleList);
    }
}
