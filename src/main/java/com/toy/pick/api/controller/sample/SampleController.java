package com.toy.pick.api.controller.sample;

import com.toy.pick.api.ApiResponse;
import com.toy.pick.api.controller.sample.dto.request.GetSampleReqDto;
import com.toy.pick.api.controller.sample.dto.response.GetSampleResDto;
import com.toy.pick.api.service.sample.SampleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "Sample API", description = "Swagger Sample API")
@RestController
@RequiredArgsConstructor
public class SampleController {

    private final SampleService sampleService;

    @Operation(summary = "이름으로 조회", description = "이름으로 조회.")
    @Parameter(name = "name", description = "이름 조건")
    @GetMapping("/sample")
    public ApiResponse<GetSampleResDto> getSampleByName(
            @Valid GetSampleReqDto paramsDto){

        try{
            GetSampleResDto getSampleResDto = sampleService.selectSampleByName(paramsDto);
            System.out.println(getSampleResDto.toString());
            return ApiResponse.ok(getSampleResDto);
        }catch (Exception e){
            return ApiResponse.of(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    };

}
