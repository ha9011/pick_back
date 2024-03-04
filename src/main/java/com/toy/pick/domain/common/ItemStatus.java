package com.toy.pick.domain.common;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ItemStatus {

    // TODO 팔로잉 제거에 따른 필요가 없어짐
    PUBLIC("공개"),
    PRIVATE("비공개");

    private final String text;
}
