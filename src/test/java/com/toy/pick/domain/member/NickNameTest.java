package com.toy.pick.domain.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NickNameTest {


    @Test
    @DisplayName("NickName을 생성한다.")
    void makeNickname() {
        // given

        // when
        String nickname = NickName.makeNickname();

        // then
        assertThat(nickname).isNotNull();
    }
}