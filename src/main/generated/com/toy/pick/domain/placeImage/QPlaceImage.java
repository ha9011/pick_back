package com.toy.pick.domain.placeImage;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPlaceImage is a Querydsl query type for PlaceImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPlaceImage extends EntityPathBase<PlaceImage> {

    private static final long serialVersionUID = 1560484884L;

    public static final QPlaceImage placeImage = new QPlaceImage("placeImage");

    public final com.toy.pick.domain.QBaseEntity _super = new com.toy.pick.domain.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath path = createString("path");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QPlaceImage(String variable) {
        super(PlaceImage.class, forVariable(variable));
    }

    public QPlaceImage(Path<? extends PlaceImage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPlaceImage(PathMetadata metadata) {
        super(PlaceImage.class, metadata);
    }

}

