package com.toy.pick.api.service.member.response;


import com.toy.pick.domain.member.Member;
import lombok.Builder;
import lombok.Getter;

import java.security.PublicKey;

@Getter
public class GetUserInfoByIdRes {
    private final Long id;
    private final String nickName;

    private final boolean tutorialYn;

    @Builder
    public GetUserInfoByIdRes(Long id, String nickName, Boolean tutorialYn) {
        this.id = id;
        this.nickName = nickName;
        this.tutorialYn = tutorialYn;
    }

    public static GetUserInfoByIdRes of(Member member){
        return GetUserInfoByIdRes.builder()
                .id(member.getId())
                .nickName(member.getNickname())
                .tutorialYn(member.getTutorialYn())
                .build();
    }
}
