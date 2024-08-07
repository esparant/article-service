package com.tak.article.domain.article.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPost is a Querydsl query type for Post
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPost extends EntityPathBase<Post> {

    private static final long serialVersionUID = 617382942L;

    public static final QPost post = new QPost("post");

    public final QPostDateEntity _super = new QPostDateEntity(this);

    public final ListPath<com.tak.article.domain.comment.entity.Comment, com.tak.article.domain.comment.entity.QComment> comments = this.<com.tak.article.domain.comment.entity.Comment, com.tak.article.domain.comment.entity.QComment>createList("comments", com.tak.article.domain.comment.entity.Comment.class, com.tak.article.domain.comment.entity.QComment.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDate = _super.updateDate;

    public final NumberPath<Long> views = createNumber("views", Long.class);

    public final StringPath writer = createString("writer");

    public QPost(String variable) {
        super(Post.class, forVariable(variable));
    }

    public QPost(Path<? extends Post> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPost(PathMetadata metadata) {
        super(Post.class, metadata);
    }

}

