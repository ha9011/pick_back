package com.toy.pick.api.service.login.response;

import com.toy.pick.api.ApiResponse;
import com.toy.pick.api.service.login.dto.UserInfo;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class JwtTokenRes {

    private String accessToken;
    private String refreshToken;

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
