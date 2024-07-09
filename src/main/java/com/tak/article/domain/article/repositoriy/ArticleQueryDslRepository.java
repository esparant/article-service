package com.tak.article.domain.article.repositoriy;

import static com.tak.article.domain.article.entity.QPost.post;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tak.article.domain.article.entity.Post;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleQueryDslRepository {

    private final JPAQueryFactory queryFactory;

    public ArticleQueryDslRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<Post> SearchPost(String title) {
        return queryFactory.selectFrom(post)
                .where(post.title.contains(title))
                .orderBy(post.id.desc())
                .fetch();
    }

}
