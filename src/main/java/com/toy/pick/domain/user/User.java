package com.toy.pick.domain.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String userId; // sns 제공한 userId

    private String provider; // sns 가입 경로

    private String name; // 닉네임
    private String refreshToken;


    public void updateRefreshToken(String refreshToken){
        this.refreshToken = refreshToken;
    }
    public User(String userId, String provider, String name, String refreshToken) {
        this.userId = userId;
        this.provider = provider;
        this.name = name;
        this.refreshToken = refreshToken;
    }

    public static User create(String userId, String provider, String name, String refreshToken){
        return new User(userId, provider, name, refreshToken);
    }
}
