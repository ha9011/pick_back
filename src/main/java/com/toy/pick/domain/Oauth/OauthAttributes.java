package com.toy.pick.domain.Oauth;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toy.pick.api.service.login.dto.KakaoUserInfo;
import com.toy.pick.api.service.login.dto.NaverUserInfo;
import com.toy.pick.api.service.login.dto.UserInfo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Getter
@RequiredArgsConstructor
public enum OauthAttributes {

    NAVER("naver") {
        /**
         * {"resultcode":"00",
         * "message":"success",
         * "response":{"id":"JIBeqj1fN4e78w8_30Iku1olnYXrOyyrR_U1OHluBRc",
         *      "email":"ha90111@naver.com","mobile":"010-7442-0597",
         *      "mobile_e164":"+821074420597","name":"\ud558\ub3d9\uc6d0"}
         *  }
         */
        @Override
        public UserInfo of(Map<String, Object> attributes, String provider) throws JsonProcessingException {
            ObjectMapper objectMapper = new ObjectMapper();
            Object response = attributes.get("response");
            String json = objectMapper.writeValueAsString(response);
            NaverUserInfo naverUserInfo = objectMapper.readValue(json, NaverUserInfo.class);
            return UserInfo.of(naverUserInfo, provider);
        }
    },

    KAKAO("kakao") {
        /**
         * {"resultcode":"00",
         * "message":"success",
         * "response":{"id":"JIBeqj1fN4e78w8_30Iku1olnYXrOyyrR_U1OHluBRc",
         *      "email":"ha90111@naver.com","mobile":"010-7442-0597",
         *      "mobile_e164":"+821074420597","name":"\ud558\ub3d9\uc6d0"}
         *  }
         */
        @Override
        public UserInfo of(Map<String, Object> attributes, String provider) throws JsonProcessingException {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(attributes);
            KakaoUserInfo kakaoUserInfo = objectMapper.readValue(json, KakaoUserInfo.class);
            return UserInfo.of(kakaoUserInfo, provider);
        }
    };

    private final String provider;

    public static OauthAttributes fromString(String provider) {
        for (OauthAttributes socialProvider : OauthAttributes.values()) {
            if (socialProvider.getProvider().equalsIgnoreCase(provider)) {
                return socialProvider;
            }
        }
        throw new IllegalArgumentException("Invalid provider: " + provider);
    }


    public abstract UserInfo of(Map<String, Object> attributes, String provider) throws JsonProcessingException;
}
