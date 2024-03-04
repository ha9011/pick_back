package com.toy.pick.domain.memberPlace;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberPlace is a Querydsl query type for MemberPlace
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberPlace extends EntityPathBase<MemberPlace> {

    private static final long serialVersionUID = -598639406L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberPlace memberPlace = new QMemberPlace("memberPlace");

    public final com.toy.pick.domain.QBaseEntity _super = new com.toy.pick.domain.QBaseEntity(this);

    public final DateTimePath<java.time.LocalDateTime> approach50mAt = createDateTime("approach50mAt", java.time.LocalDateTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.toy.pick.domain.member.QMember member;

    public final com.toy.pick.domain.place.QPlace place;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QMemberPlace(String variable) {
        this(MemberPlace.class, forVariable(variable), INITS);
    }

    public QMemberPlace(Path<? extends MemberPlace> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberPlace(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberPlace(PathMetadata metadata, PathInits inits) {
        this(MemberPlace.class, metadata, inits);
    }

    public QMemberPlace(Class<? extends MemberPlace> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.toy.pick.domain.member.QMember(forProperty("member")) : null;
        this.place = inits.isInitialized("place") ? new com.toy.pick.domain.place.QPlace(forProperty("place")) : null;
    }

}

