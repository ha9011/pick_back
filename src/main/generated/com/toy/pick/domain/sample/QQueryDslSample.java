package com.toy.pick.domain.sample;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QQueryDslSample is a Querydsl query type for QueryDslSample
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQueryDslSample extends EntityPathBase<QueryDslSample> {

    private static final long serialVersionUID = 1837136009L;

    public static final QQueryDslSample queryDslSample = new QQueryDslSample("queryDslSample");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QQueryDslSample(String variable) {
        super(QueryDslSample.class, forVariable(variable));
    }

    public QQueryDslSample(Path<? extends QueryDslSample> path) {
        super(path.getType(), path.getMetadata());
    }

    public QQueryDslSample(PathMetadata metadata) {
        super(QueryDslSample.class, metadata);
    }

}

