package com.toy.pick.domain.common;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ItemStatus {

    PUBLIC("공개"),
    PRIVATE("비공개");

    private final String text;
}
