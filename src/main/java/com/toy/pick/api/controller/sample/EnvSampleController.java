package com.toy.pick.api.controller.sample;

import com.toy.pick.api.ApiResponse;
import com.toy.pick.api.controller.sample.dto.request.GetSampleReqDto;
import com.toy.pick.api.controller.sample.dto.response.GetSampleResDto;
import com.toy.pick.api.service.sample.SampleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class EnvSampleController {

    @Value("${spring.profiles.default}")
    private String envDefault;
    @Value("${spring.profiles.active}")
    private String envActive;
    @Value("${spring.test.text}")
    private String tt;

    @GetMapping("/sample/env")
    public String getEnv(){

        return envDefault + " : " + envActive + " : "+ tt;
    };


}
