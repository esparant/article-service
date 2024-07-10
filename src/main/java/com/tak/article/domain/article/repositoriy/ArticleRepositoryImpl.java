package com.tak.article.domain.article.repositoriy;

import static com.tak.article.domain.article.entity.QPost.post;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tak.article.domain.article.entity.Post;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

public class ArticleRepositoryImpl implements ArticleRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ArticleRepositoryImpl(EntityManager em) {
        queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Post> findByTitle(String title) {
        return queryFactory.selectFrom(post)
                .where(containTitle(title))
                .orderBy(post.id.desc())
                .fetch();
    }

    @Override
    public Page<Post> searchPage(String title, Pageable pageable) {
        List<Post> content = queryFactory.selectFrom(post)
                .where(containTitle(title))
                .orderBy(
                        new CaseBuilder()
                                .when(post.title.eq(title)).then(0)
                                .otherwise(1).asc(),
                        post.id.desc()
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = queryFactory.select(post.count())
                .from(post)
                .where(containTitle(title));

        return PageableExecutionUtils.getPage(content, pageable, count::fetchOne);
    }

    private static BooleanExpression containTitle(String title) {
        return post.title.contains(title);
    }
}
