package com.toy.pick.domain.sample;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSample is a Querydsl query type for Sample
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSample extends EntityPathBase<Sample> {

    private static final long serialVersionUID = 1081862740L;

    public static final QSample sample = new QSample("sample");

    public final com.toy.pick.domain.QBaseEntity _super = new com.toy.pick.domain.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDateTime = _super.createdDateTime;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDateTime = _super.modifiedDateTime;

    public final StringPath name = createString("name");

    public final EnumPath<SampleStatus> sampleStatus = createEnum("sampleStatus", SampleStatus.class);

    public QSample(String variable) {
        super(Sample.class, forVariable(variable));
    }

    public QSample(Path<? extends Sample> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSample(PathMetadata metadata) {
        super(Sample.class, metadata);
    }

}

