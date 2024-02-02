package com.toy.pick.api.service.login.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class JwtTokenRes {

    private final String accessToken;
    private final String refreshToken;

    @Builder
    public JwtTokenRes(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }



    public static JwtTokenRes of(String accessToken, String refreshToken){
        return JwtTokenRes.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
