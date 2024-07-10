package com.tak.article.domain.article.repositoriy;

import com.tak.article.domain.article.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Post, Long>, ArticleRepositoryCustom {


}
