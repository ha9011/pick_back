package com.toy.pick.api.service.login.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class UserInfo {
    private final String oauthId;
    private final String provider;

    @Builder
    public UserInfo(String oauthId, String provider) {
        this.oauthId = oauthId;
        this.provider = provider;
    }

    public static UserInfo of(NaverUserInfo userInfo, String provider){
        return UserInfo.builder()
                .oauthId(userInfo.getId())
                .provider(provider)
                .build();
    }

}

