package com.toy.pick.api.service.collection.response;

import com.toy.pick.domain.collection.Collection;
import com.toy.pick.domain.common.ItemStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@ToString
public class FollowCollectionRes {
    private final Long id;
    private final String title;
    private final String memo;
    private final String lastUpdateTime;

    @Builder
    public FollowCollectionRes(Collection collection) {
        this.id = collection.getId();
        this.title = collection.getTitle();
        this.memo = collection.getMemo();
        this.lastUpdateTime = this.transferUpdateTime(collection.getUpdatedAt());
    }

    public static FollowCollectionRes of(Collection collection){
        return new FollowCollectionRes(collection);
    }

    String transferUpdateTime(LocalDateTime updateAt) {

        LocalDateTime currentTime = LocalDateTime.now();

        // 시간 차이 계산
        Duration betweenTime = Duration.between(updateAt, currentTime);

        if (betweenTime.toHours() < 1) {
            return "방금 업데이트";
        } else if (betweenTime.toHours() < 24) {
            return "오늘 업데이트";
        } else if (betweenTime.toDays() < 7) {
            return "1주 전 업데이트";
        } else if (betweenTime.toDays() < 30) {
            return "1개월 전 업데이트";
        } else if (betweenTime.toDays() < 60) {
            return "2개월 전 업데이트";
        } else if (betweenTime.toDays() < 90) {
            return "3개월 전 업데이트";
        } else if (betweenTime.toDays() < 120) {
            return "4개월 전 업데이트";
        } else if (betweenTime.toDays() < 150) {
            return "5개월 전 업데이트";
        } else if (betweenTime.toDays() < 180) {
            return "6개월 전 업데이트";
        } else if (betweenTime.toDays() < 210) {
            return "7개월 전 업데이트";
        } else if (betweenTime.toDays() < 240) {
            return "8개월 전 업데이트";
        } else if (betweenTime.toDays() < 270) {
            return "9개월 전 업데이트";
        } else if (betweenTime.toDays() < 300) {
            return "10개월 전 업데이트";
        } else if (betweenTime.toDays() < 330) {
            return "11개월 전 업데이트";
        } else if (betweenTime.toDays() < 360) {
            return "12개월 전 업데이트";
        } else {
            return "1년 전 업데이트";
        }
    }

}
