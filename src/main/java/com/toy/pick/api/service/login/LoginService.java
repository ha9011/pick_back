package com.toy.pick.api.service.login;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.toy.pick.api.service.login.dto.OauthPropertiesDto;
import com.toy.pick.api.service.login.dto.UserInfo;
import com.toy.pick.api.service.login.response.JwtTokenRes;
import com.toy.pick.api.service.sns.SnsLoginService;
import com.toy.pick.component.JwtTokenProvider;
import com.toy.pick.component.Oauth2Properties;
import com.toy.pick.domain.collection.Collection;
import com.toy.pick.domain.collection.CollectionRepository;
import com.toy.pick.domain.member.Member;
import com.toy.pick.domain.member.MemberRepository;
import com.toy.pick.domain.member.NickName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    private final Oauth2Properties oauth2Properties;

    private final JwtTokenProvider jwtTokenProvider;

    private final MemberRepository memberRepository;

    private final SnsLoginService snsLoginService;

    private final CollectionRepository collectionRepository;

    @Transactional
    public JwtTokenRes loginSnsOauth(String provider, String code) throws JsonProcessingException {

        Map<String, OauthPropertiesDto> OauthProperties = oauth2Properties.getProviders();
        OauthPropertiesDto oauthProperties = OauthProperties.get(provider); // 해당 소셜에 대한 Oauth 정보

        // Provider 에게 토큰 획득 하기
        String snsAccessToken = snsLoginService.getSnsAccessToken(code, oauthProperties);

        // 획득한 토큰으로 사용자 정도 얻기
        UserInfo userInfo = snsLoginService.getSnsUserInfo(snsAccessToken, oauthProperties, provider);

        // DB 가입
        // 1. 이미 가입됐는지 확인,
        Member member = memberRepository.findByUserId(userInfo.getUserId());
        String accessToken = null;
        String refreshToken = null;
        if(member == null){ // 새로 생성

            // 맴버 먼저 생성
            // 맴버 PK 추출 후, payload에 추가하여
            // access & refresh Token 생성
            String nickname = NickName.makeNickname(); // TODO enum 값 설정하기
            Member createdMember = Member.create(userInfo.getUserId(), userInfo.getProvider(), nickname);
            Member newMember = memberRepository.save(createdMember);
            UserInfo payLoad = UserInfo.of(newMember.getId(), newMember.getUserId(), newMember.getProvider());

            // JWT 만들기
            accessToken = jwtTokenProvider.createAccessToken(payLoad);
            refreshToken = jwtTokenProvider.createRefreshToken(payLoad);
            newMember.updateAccessToken(accessToken);
            newMember.updateRefreshToken(refreshToken);

            //기본 컬랙션 생성
            Collection newCollection = Collection.defaultCreate(newMember);
            collectionRepository.save(newCollection);


        }else{ // 토큰 업데이트

            UserInfo payLoad = UserInfo.of(member.getId(), member.getUserId(), member.getProvider());
            accessToken = jwtTokenProvider.createAccessToken(payLoad);
            refreshToken = jwtTokenProvider.createRefreshToken(payLoad);
            member.updateRefreshToken(refreshToken);
            member.updateAccessToken(accessToken);
        }

        return JwtTokenRes.of(accessToken, refreshToken);
    }




    public UserInfo userTokenInfo(String accessToken) {
        UserInfo allPayload = jwtTokenProvider.getAllPayload(accessToken);

        return allPayload;
    }
}
