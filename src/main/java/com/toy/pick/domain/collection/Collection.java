package com.toy.pick.domain.collection;

import com.toy.pick.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
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
    private boolean notDeleteYn; // 기본 칼럼은 삭제 할 수 없다.

}
