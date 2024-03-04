package com.toy.pick.domain.collectionPlace;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCollectionPlace is a Querydsl query type for CollectionPlace
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCollectionPlace extends EntityPathBase<CollectionPlace> {

    private static final long serialVersionUID = -1513765814L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCollectionPlace collectionPlace = new QCollectionPlace("collectionPlace");

    public final com.toy.pick.domain.QBaseEntity _super = new com.toy.pick.domain.QBaseEntity(this);

    public final com.toy.pick.domain.collection.QCollection collection;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath memo = createString("memo");

    public final com.toy.pick.domain.place.QPlace place;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final StringPath url = createString("url");

    public QCollectionPlace(String variable) {
        this(CollectionPlace.class, forVariable(variable), INITS);
    }

    public QCollectionPlace(Path<? extends CollectionPlace> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCollectionPlace(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCollectionPlace(PathMetadata metadata, PathInits inits) {
        this(CollectionPlace.class, metadata, inits);
    }

    public QCollectionPlace(Class<? extends CollectionPlace> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.collection = inits.isInitialized("collection") ? new com.toy.pick.domain.collection.QCollection(forProperty("collection"), inits.get("collection")) : null;
        this.place = inits.isInitialized("place") ? new com.toy.pick.domain.place.QPlace(forProperty("place")) : null;
    }

}

