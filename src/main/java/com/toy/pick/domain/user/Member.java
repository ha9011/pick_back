package com.toy.pick.domain.user;

import com.toy.pick.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String userId; // sns 제공한 userId


    @Column(nullable = false)
    private String provider; // sns 가입 경로


    @Column(nullable = false)
    private String nickname; // 닉네임

    @Column(nullable = false)
    private String accessToken;

    @Column(nullable = false)
    private String refreshToken;

    @Column(columnDefinition = "boolean default false")
    private Boolean tutorialYn = false;


    public void updateRefreshToken(String refreshToken){
        this.refreshToken = refreshToken;
    }
    public Member(String userId, String provider, String nickname, String accessToken, String refreshToken) {
        this.userId = userId;
        this.provider = provider;
        this.nickname = nickname;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public static Member create(String userId, String provider, String nickname, String accessToken, String refreshToken){
        return new Member(userId, provider, nickname, accessToken, refreshToken);
    }

    public void updateAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
