package com.toy.pick.api.controller.naver;

import com.toy.pick.api.ApiResponse;
import com.toy.pick.api.service.naver.NaverGeoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NaverGeoController {

    private final NaverGeoService naverGeoService;

    @GetMapping("/reverse")
    public ApiResponse<Object> getNaveReverseSampleTest(){
        String reqBody = naverGeoService.searchReverseGeo("test");
        return ApiResponse.ok(reqBody);
    };


    @GetMapping("/geocode")
    public ApiResponse<Object> getNaveGeocodeSampleTest(
            @RequestParam String geocode
    ){
        String reqBody = naverGeoService.searchGeocode(geocode);
        return ApiResponse.ok(reqBody);
    };
}
