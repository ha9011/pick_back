package com.toy.pick.domain.sample;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SampleStatus {

    NOT_SAMPLE("샘플X"),
    SAMPLE("샘플O");

    private final String text;
}
