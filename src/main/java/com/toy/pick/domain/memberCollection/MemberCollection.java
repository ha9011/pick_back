package com.toy.pick.domain.memberCollection;


import com.toy.pick.domain.BaseEntity;
import com.toy.pick.domain.collection.Collection;
import com.toy.pick.domain.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MemberCollection extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collection_id")
    private Collection collection;

    @Builder
    public MemberCollection(Member member, Collection collection) {
        this.member = member;
        this.collection = collection;
    }

    public static MemberCollection create(Member member, Collection collection){
        return MemberCollection.builder()
                .member(member)
                .collection(collection)
                .build();
    }
}
