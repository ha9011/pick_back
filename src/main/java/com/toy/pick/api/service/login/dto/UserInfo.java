package com.toy.pick.api.service.login.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfo {
    private  String userId;
    private  String provider;

    @Builder
    public UserInfo(String userId, String provider) {
        this.userId = userId;
        this.provider = provider;
    }

    public static UserInfo of(NaverUserInfo userInfo, String provider){
        return UserInfo.builder()
                .userId(userInfo.getId())
                .provider(provider)
                .build();
    }

}

