package com.toy.pick.domain.collection;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum CollectionStatus {

    PUBLIC("공개"),
    PRIVATE("비공개");

    private final String text;
}
