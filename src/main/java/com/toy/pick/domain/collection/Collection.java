package com.toy.pick.domain.collection;

import com.toy.pick.api.controller.collection.request.PostMyCollectionsReq;
import com.toy.pick.domain.BaseEntity;
import com.toy.pick.domain.collectionPlace.CollectionPlace;
import com.toy.pick.domain.common.ItemStatus;
import com.toy.pick.domain.member.Member;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Collection extends BaseEntity {

    // TODO 고민 -> 아이디당 지울수 없는 컬렉션이 하나가 고정되어 있다. => 아이디 생성시 자동으로 만들어지게 수정
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String memo;

    @ColumnDefault("false")
    private boolean isDeletable; // 기본 칼럼은 삭제 할 수 없다.

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @OneToMany(mappedBy = "collection")
    private List<CollectionPlace> collectionPlaces;

    @Column
    private LocalDateTime lastUpdateAt;
    @Builder
    public Collection(String title, String memo, boolean isDeletable, Member member, LocalDateTime now) {
        this.title = title;
        this.memo = memo;
        this.isDeletable = isDeletable;
        this.member = member;
        this.lastUpdateAt = now;
    }


    public static Collection create(PostMyCollectionsReq req, Member member){
        return new Collection(req.getTitle(), req.getMemo(), true, member,LocalDateTime.now());

    }

    // 맴버 계정 생성시, 최초 생성되는 기본 컬랙션
    public static Collection defaultCreate(Member member){
        return new Collection("기본",  "", false, member,LocalDateTime.now());
    }

    public void refreshLastUpdateAt(LocalDateTime now){
        this.lastUpdateAt = now;
    }


    public void updateCollectionInfo(String title, String memo){
        this.title = title;
        this.memo = memo;
        this.refreshLastUpdateAt(LocalDateTime.now());
    }


}

