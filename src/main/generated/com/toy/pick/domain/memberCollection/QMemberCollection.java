package com.toy.pick.domain.memberCollection;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberCollection is a Querydsl query type for MemberCollection
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberCollection extends EntityPathBase<MemberCollection> {

    private static final long serialVersionUID = 542441492L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberCollection memberCollection = new QMemberCollection("memberCollection");

    public final com.toy.pick.domain.QBaseEntity _super = new com.toy.pick.domain.QBaseEntity(this);

    public final com.toy.pick.domain.collection.QCollection collection;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.toy.pick.domain.member.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QMemberCollection(String variable) {
        this(MemberCollection.class, forVariable(variable), INITS);
    }

    public QMemberCollection(Path<? extends MemberCollection> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberCollection(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberCollection(PathMetadata metadata, PathInits inits) {
        this(MemberCollection.class, metadata, inits);
    }

    public QMemberCollection(Class<? extends MemberCollection> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.collection = inits.isInitialized("collection") ? new com.toy.pick.domain.collection.QCollection(forProperty("collection"), inits.get("collection")) : null;
        this.member = inits.isInitialized("member") ? new com.toy.pick.domain.member.QMember(forProperty("member")) : null;
    }

}

