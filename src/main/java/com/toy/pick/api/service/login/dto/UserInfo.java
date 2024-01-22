package com.toy.pick.api.service.login.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Map;

@Getter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfo {
    private  Long id; // Member entity id(pk)
    private  String userId;
    private  String provider;

    @Builder
    public UserInfo(Long id, String userId, String provider) {
        this.id = id;
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

    public static UserInfo of(Long id, String userId, String provider){
        return UserInfo.builder()
                .id(id)
                .userId(userId)
                .provider(provider)
                .build();
    }

}

