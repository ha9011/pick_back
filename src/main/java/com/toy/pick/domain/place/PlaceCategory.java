package com.toy.pick.domain.place;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PlaceCategory {

    CAFE("카페"); // TODO 카테고리 채우기
    private final String text;
}
