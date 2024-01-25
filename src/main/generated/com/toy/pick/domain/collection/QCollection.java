package com.toy.pick.domain.collection;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCollection is a Querydsl query type for Collection
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCollection extends EntityPathBase<Collection> {

    private static final long serialVersionUID = -17411628L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCollection collection = new QCollection("collection");

    public final com.toy.pick.domain.QBaseEntity _super = new com.toy.pick.domain.QBaseEntity(this);

    public final ListPath<com.toy.pick.domain.collectionPlace.CollectionPlace, com.toy.pick.domain.collectionPlace.QCollectionPlace> collectionPlaces = this.<com.toy.pick.domain.collectionPlace.CollectionPlace, com.toy.pick.domain.collectionPlace.QCollectionPlace>createList("collectionPlaces", com.toy.pick.domain.collectionPlace.CollectionPlace.class, com.toy.pick.domain.collectionPlace.QCollectionPlace.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isDeletableYn = createBoolean("isDeletableYn");

    public final com.toy.pick.domain.member.QMember member;

    public final StringPath memo = createString("memo");

    public final EnumPath<com.toy.pick.domain.common.ItemStatus> status = createEnum("status", com.toy.pick.domain.common.ItemStatus.class);

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QCollection(String variable) {
        this(Collection.class, forVariable(variable), INITS);
    }

    public QCollection(Path<? extends Collection> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCollection(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCollection(PathMetadata metadata, PathInits inits) {
        this(Collection.class, metadata, inits);
    }

    public QCollection(Class<? extends Collection> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.toy.pick.domain.member.QMember(forProperty("member")) : null;
    }

}

