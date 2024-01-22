package com.toy.pick.domain.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Random;

@Getter
@RequiredArgsConstructor
public enum NickName {

    FIRST_NAMES(List.of("매일","매번","매주","매달","매년","항상")),
    MIDDLE_NAMES(List.of("움직이는","찾아보는","돌아다니는","담아두는", "찜한","결제하는")),
    LAST_NAMES(List.of("맛집","카페","핫플","힙플","사진맛집","쇼핑몰","관광지","명소","필수코스"));

    private final List<String> names;
    private static final Random random = new Random();

    public static String makeNickname() {
        String firstName = getRandomElement(FIRST_NAMES.getNames());
        String middleName = getRandomElement(MIDDLE_NAMES.getNames());
        String lastName = getRandomElement(LAST_NAMES.getNames());

        return firstName +" "+ middleName +" "+ lastName;
    }

    private static String getRandomElement(List<String> list) {
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }

}
