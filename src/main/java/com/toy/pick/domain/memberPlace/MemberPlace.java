package com.toy.pick.domain.memberPlace;

import com.toy.pick.domain.BaseEntity;
import com.toy.pick.domain.member.Member;
import com.toy.pick.domain.place.Place;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberPlace extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    private String memo;

    private String url;

    private LocalDateTime approach50mAt;

    @Builder
    public MemberPlace(Member member, Place place, String memo, String url, LocalDateTime approach50mAt) {
        this.member = member;
        this.place = place;
        this.memo = memo;
        this.url = url;
        this.approach50mAt = approach50mAt;
    }

    public static MemberPlace create(Member member, Place place, String memo, String url){
        return MemberPlace.builder()
                .member(member)
                .place(place)
                .memo(memo)
                .url(url)
                .approach50mAt(null)
                .build();
    }
}
