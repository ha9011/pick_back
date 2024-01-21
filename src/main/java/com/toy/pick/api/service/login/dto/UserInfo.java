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


    // TODO <- 다형성으로 만들기
    public static UserInfo of(SnsUserInfo userInfo, String provider){
        return UserInfo.builder()
                .userId(userInfo.getId())
                .provider(provider)
                .build();
    }

    public static UserInfo of(String userId, String provider){
        return UserInfo.builder()
                .userId(userId)
                .provider(provider)
                .build();
    }

}

