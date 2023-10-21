package com.toy.pick.api.controller.sample;

import com.toy.pick.api.ApiResponse;
import com.toy.pick.api.controller.sample.dto.request.GetSampleReqDto;
import com.toy.pick.api.controller.sample.dto.response.GetSampleResDto;
import com.toy.pick.api.service.sample.SampleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SampleController {

    private final SampleService sampleService;

    @GetMapping("/sample")
    public ApiResponse<GetSampleResDto> getSampleByName(
            @Valid GetSampleReqDto paramsDto){

        try{
            GetSampleResDto getSampleResDto = sampleService.selectSampleByName(paramsDto);
            return ApiResponse.ok(getSampleResDto);
        }catch (Exception e){
            return ApiResponse.of(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    };
}
