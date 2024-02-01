package com.toy.pick.domain.place;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPlace is a Querydsl query type for Place
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPlace extends EntityPathBase<Place> {

    private static final long serialVersionUID = 680325766L;

    public static final QPlace place = new QPlace("place");

    public final com.toy.pick.domain.QBaseEntity _super = new com.toy.pick.domain.QBaseEntity(this);

    public final StringPath address = createString("address");

    public final EnumPath<PlaceCategory> category = createEnum("category", PlaceCategory.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final StringPath detailAddress = createString("detailAddress");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<com.toy.pick.domain.common.ItemStatus> itemStatus = createEnum("itemStatus", com.toy.pick.domain.common.ItemStatus.class);

    public final StringPath name = createString("name");

    public final ListPath<com.toy.pick.domain.placeImage.PlaceImage, com.toy.pick.domain.placeImage.QPlaceImage> placeImages = this.<com.toy.pick.domain.placeImage.PlaceImage, com.toy.pick.domain.placeImage.QPlaceImage>createList("placeImages", com.toy.pick.domain.placeImage.PlaceImage.class, com.toy.pick.domain.placeImage.QPlaceImage.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final StringPath x = createString("x");

    public final StringPath y = createString("y");

    public QPlace(String variable) {
        super(Place.class, forVariable(variable));
    }

    public QPlace(Path<? extends Place> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPlace(PathMetadata metadata) {
        super(Place.class, metadata);
    }

}

