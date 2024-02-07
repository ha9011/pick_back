package com.toy.pick.api.service.collection.response;

import com.toy.pick.domain.collection.Collection;
import com.toy.pick.domain.common.ItemStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FollowCollectionRes {
    private final Long id;
    private final String title;
    private final String memo;
    private final String lastUpdateTime = "최근 업데이트(TODO)"; // TODO MyCollectionsRes 최근 업데이트 시점

    @Builder
    public FollowCollectionRes(Collection collection) {
        this.id = collection.getId();
        this.title = collection.getTitle();
        this.memo = collection.getMemo();
        //this.lastUpdateTime = this.transferUpdateTime(collection.getUpdatedAt());
    }

    public static FollowCollectionRes of(Collection collection){
        return new FollowCollectionRes(collection);
    }

}
