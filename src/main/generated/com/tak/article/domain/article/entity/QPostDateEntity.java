package com.tak.article.domain.article.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPostDateEntity is a Querydsl query type for PostDateEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QPostDateEntity extends EntityPathBase<PostDateEntity> {

    private static final long serialVersionUID = 1930762223L;

    public static final QPostDateEntity postDateEntity = new QPostDateEntity("postDateEntity");

    public final DateTimePath<java.time.LocalDateTime> createDate = createDateTime("createDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> updateDate = createDateTime("updateDate", java.time.LocalDateTime.class);

    public QPostDateEntity(String variable) {
        super(PostDateEntity.class, forVariable(variable));
    }

    public QPostDateEntity(Path<? extends PostDateEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPostDateEntity(PathMetadata metadata) {
        super(PostDateEntity.class, metadata);
    }

}

