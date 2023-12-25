package com.toy.pick.api.service.naver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class NaverGeoService {

    private final String reverseApiUrl = "https://naveropenapi.apigw.ntruss.com/map-reversegeocode/v2/gc?request=coordsToaddr&coords=129.1133567,35.2982640&sourcecrs=epsg:4326&output=json&orders=legalcode,admcode"; // 예시 URL, 실제 사용할 API에 따라 다름
    private final String geocodeApiUrl = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode"; // 예시 URL, 실제 사용할 API에 따라 다름
    private final String clientId = "jh0j3uo46l";
    private final String clientSecret = "7Cup9VTKB8Tu9TF2RyBdstGlOWtVApZf6J73BQn3";

    public String searchReverseGeo(String query) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-NCP-APIGW-API-KEY-ID", clientId);
        headers.set("X-NCP-APIGW-API-KEY", clientSecret);


        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                reverseApiUrl,
                HttpMethod.GET,
                request,
                String.class
        );


        // 결과 처리
        if (response.getStatusCode().is2xxSuccessful()) {
            log.info(" Naver Api GeoReverse - Success API ");
            log.info(response.getBody());
            return response.getBody();
        } else {
            // 오류 처리
            return "Error: " + response.getStatusCode();
        }
    }

    public String searchGeocode(String geoName) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-NCP-APIGW-API-KEY-ID", clientId);
        headers.set("X-NCP-APIGW-API-KEY", clientSecret);


        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                geocodeApiUrl+"?query="+geoName,
                HttpMethod.GET,
                request,
                String.class
        );


        // 결과 처리
        if (response.getStatusCode().is2xxSuccessful()) {
            log.info(" Naver Api GeoReverse - Success API ");
            log.info(response.getBody());
            return response.getBody();
        } else {
            // 오류 처리
            return "Error: " + response.getStatusCode();
        }
    }
}
