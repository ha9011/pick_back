package com.toy.pick.domain.member;

import com.toy.pick.domain.BaseEntity;
import com.toy.pick.domain.collection.Collection;
import com.toy.pick.domain.memberPlace.MemberPlace;
import com.toy.pick.domain.place.Place;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

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

    private String accessToken;

    private String refreshToken;


    @OneToMany(mappedBy = "member")
    private List<Collection> collections; // 팔로우


    @OneToMany(mappedBy = "member")
    private List<MemberPlace> memberPlaces; // 팔로우

    public void updateRefreshToken(String refreshToken){
        this.refreshToken = refreshToken;
    }
    public Member(String userId, String provider, String nickname) {
        this.userId = userId;
        this.provider = provider;
        this.nickname = nickname;
    }

    public static Member create(String userId, String provider, String nickname){
        return new Member(userId, provider, nickname);
    }

    public void updateAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }


}
