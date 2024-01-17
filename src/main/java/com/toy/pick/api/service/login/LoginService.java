package com.toy.pick.api.service.login;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toy.pick.api.ApiResponse;
import com.toy.pick.api.service.login.dto.OauthPropertiesDto;
import com.toy.pick.api.service.login.dto.OauthTokenDto;
import com.toy.pick.api.service.login.dto.UserInfo;
import com.toy.pick.api.service.login.response.JwtTokenRes;
import com.toy.pick.component.JwtTokenProvider;
import com.toy.pick.component.Oauth2Properties;
import com.toy.pick.domain.Oauth.OauthAttributes;
import com.toy.pick.domain.user.Member;
import com.toy.pick.domain.user.MemberRepository;
import com.toy.pick.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    private final Oauth2Properties oauth2Properties;
    private final ObjectMapper om;
    private final RestTemplate restTemplate = new RestTemplate();

    private final JwtTokenProvider jwtTokenProvider;

    private final MemberRepository memberRepository;

    @Transactional
    public ApiResponse<JwtTokenRes> loginSnsOauth(String provider, String code) throws JsonProcessingException {

        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        Map<String, OauthPropertiesDto> OauthProperties = oauth2Properties.getProviders();
        OauthPropertiesDto oauthProperties = OauthProperties.get(provider); // 해당 소셜에 대한 Oauth 정보

        // Provider 에게 토큰 획득 하기
        String snsAccessToken = this.getSnsAccessToken(code, oauthProperties);

        // 획득한 토큰으로 사용자 정도 얻기
        UserInfo userInfo = this.getSnsUserInfo(snsAccessToken, oauthProperties, provider);

        // JWT 만들기
        String accessToken = jwtTokenProvider.createAccessToken(userInfo);
        String refreshToken = jwtTokenProvider.createRefreshToken(userInfo);

        // DB 가입
        // 1. 이미 가입됐는지 확인,
        Member member = memberRepository.findByUserId(userInfo.getUserId());
        if(member == null){ // 새로 생성
            Member createdMember = Member.create(userInfo.getUserId(), userInfo.getProvider(), "닉네임", accessToken, refreshToken);
            memberRepository.save(createdMember);
        }else{ // 토큰 업데이트 업데이트
            member.updateRefreshToken(refreshToken);
            member.updateAccessToken(accessToken);
        }

        return ApiResponse.ok(JwtTokenRes.of(accessToken, refreshToken));
    }

    private UserInfo getSnsUserInfo(String accessToken, OauthPropertiesDto oauthProperties, String provider) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+ accessToken);

        HttpEntity request = new HttpEntity(headers);
        RestTemplate restTemplate = new RestTemplate();

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
    };

    private String getSnsAccessToken(String code, OauthPropertiesDto oauthProperties) throws JsonProcessingException {
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
    }

    public ApiResponse<UserInfo> userTokenInfo(String accessToken) {
        UserInfo allPayload = jwtTokenProvider.getAllPayload(accessToken);

        return ApiResponse.ok(allPayload);
    }
}
