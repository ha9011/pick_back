package com.toy.pick.domain.member;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -1699251116L;

    public static final QMember member = new QMember("member1");

    public final com.toy.pick.domain.QBaseEntity _super = new com.toy.pick.domain.QBaseEntity(this);

    public final StringPath accessToken = createString("accessToken");

    public final ListPath<com.toy.pick.domain.collection.Collection, com.toy.pick.domain.collection.QCollection> collections = this.<com.toy.pick.domain.collection.Collection, com.toy.pick.domain.collection.QCollection>createList("collections", com.toy.pick.domain.collection.Collection.class, com.toy.pick.domain.collection.QCollection.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.toy.pick.domain.memberPlace.MemberPlace, com.toy.pick.domain.memberPlace.QMemberPlace> memberPlaces = this.<com.toy.pick.domain.memberPlace.MemberPlace, com.toy.pick.domain.memberPlace.QMemberPlace>createList("memberPlaces", com.toy.pick.domain.memberPlace.MemberPlace.class, com.toy.pick.domain.memberPlace.QMemberPlace.class, PathInits.DIRECT2);

    public final StringPath nickname = createString("nickname");

    public final StringPath provider = createString("provider");

    public final StringPath refreshToken = createString("refreshToken");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final StringPath userId = createString("userId");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

