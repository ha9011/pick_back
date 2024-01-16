package com.toy.pick.domain.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String userId; // sns 제공한 userId

    private String provider; // sns 가입 경로

    private String name; // 닉네임
    private String accessToken;
    private String refreshToken;



    public void updateRefreshToken(String refreshToken){
        this.refreshToken = refreshToken;
    }
    public Member(String userId, String provider, String name, String accessToken, String refreshToken) {
        this.userId = userId;
        this.provider = provider;
        this.name = name;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public static Member create(String userId, String provider, String name, String accessToken, String refreshToken){
        return new Member(userId, provider, name, accessToken, refreshToken);
    }

    public void updateAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
