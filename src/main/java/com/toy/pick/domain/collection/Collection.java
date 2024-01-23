package com.toy.pick.domain.collection;

import com.toy.pick.api.controller.collection.request.PostMyCollectionsReq;
import com.toy.pick.domain.BaseEntity;
import com.toy.pick.domain.member.Member;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CollectionStatus status; // 노말

    private String memo;

    @ColumnDefault("false")
    private boolean isDeletableYn; // 기본 칼럼은 삭제 할 수 없다.

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Collection(String title, CollectionStatus status, String memo, boolean isDeletableYn, Member member) {
        this.title = title;
        this.status = status;
        this.memo = memo;
        this.isDeletableYn = isDeletableYn;
        this.member = member;
    }

    public static Collection create(PostMyCollectionsReq req, Member member){
        return Collection.builder()
                .title(req.getTitle())
                .memo(req.getMemo())
                .status(CollectionStatus.valueOf(req.getStatus()))
                .isDeletableYn(true)
                .member(member)
                .build();
    }
    // 맴버 계정 생성시, 최초 생성되는 기본 컬랙션
    public static Collection defaultCreate(Member member){
        return Collection.builder()
                .title("기본")
                .memo("")
                .status(CollectionStatus.PRIVATE)
                .isDeletableYn(false)
                .member(member)
                .build();
    }
}

