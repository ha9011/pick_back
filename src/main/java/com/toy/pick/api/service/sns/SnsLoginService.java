package com.toy.pick.api.service.sns;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toy.pick.api.service.login.dto.OauthPropertiesDto;
import com.toy.pick.api.service.login.dto.OauthTokenDto;
import com.toy.pick.api.service.login.dto.UserInfo;
import com.toy.pick.domain.Oauth.OauthAttributes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@Service
@Slf4j
@RequiredArgsConstructor
public class SnsLoginService {

    private final RestTemplate restTemplate = new RestTemplate();

    private final ObjectMapper om;

    public String getSnsAccessToken(String code, OauthPropertiesDto oauthProperties) throws Exception {
        try{
            restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());

            MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();

            parameters.add("code", code);
            parameters.add("grant_type", "authorization_code");
            parameters.add("client_id", oauthProperties.getClientId());
            parameters.add("client_secret", oauthProperties.getClientSecret());

            String url = oauthProperties.getTokenUri();

            ResponseEntity<String> res = restTemplate.postForEntity(url, parameters, String.class);
            log.info(res.getBody());
            if(res.getStatusCode().is2xxSuccessful()){
                OauthTokenDto oauthTokenDto = om.readValue(res.getBody(), OauthTokenDto.class);
                return oauthTokenDto.getAccess_token(); // sns accessToken
            }else{
                log.error("소셜 로그인에 실패 했습니다. 응답 코드: {}", res.getStatusCode()); // TODO 명칭변경, error handler 만들기
                throw new HttpClientErrorException(res.getStatusCode(), "소셜 로그인에 실패 했습니다.");
            }
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    public UserInfo getSnsUserInfo(String accessToken, OauthPropertiesDto oauthProperties, String provider) throws Exception {
        try {
            restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer "+ accessToken);

            HttpEntity request = new HttpEntity(headers);

            String url = oauthProperties.getUserInfoUri();
            ResponseEntity<String> res = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    request,
                    String.class
            );

            log.info(res.getBody());
            if(res.getStatusCode().is2xxSuccessful()){
                Map<String, Object> resultMap = om.readValue(res.getBody(), Map.class);
                return OauthAttributes.fromString(provider).of(resultMap, provider);
            }else{
                log.error("소셜 로그인에 실패 했습니다. 응답 코드: {}", res.getStatusCode()); // TODO 명칭변경, error handler 만들기
                throw new HttpClientErrorException(res.getStatusCode(), "소셜 로그인에 실패 했습니다.");
            }
        }catch (Exception e){
            throw new Exception(e);
        }

    };
}
