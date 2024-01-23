package com.toy.pick.api.service.collection.response;

import com.toy.pick.domain.collection.Collection;
import com.toy.pick.domain.collection.CollectionStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MyCollectionsRes {
    private final Long id;

    private final String title;

    private final CollectionStatus status; // 노말
    private final String memo;

    private final boolean isDeletableYn; // 기본 칼럼은 삭제 할 수 없다.

    @Builder
    public MyCollectionsRes(Long id, String title, CollectionStatus status, String memo, boolean isDeletableYn) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.memo = memo;
        this.isDeletableYn = isDeletableYn;
    }

    public static MyCollectionsRes of(Collection collection){
        return MyCollectionsRes.builder()
                .id(collection.getId())
                .title(collection.getTitle())
                .status(collection.getStatus())
                .memo(collection.getMemo())
                .isDeletableYn(collection.isDeletableYn())
                .build();
    }
}
