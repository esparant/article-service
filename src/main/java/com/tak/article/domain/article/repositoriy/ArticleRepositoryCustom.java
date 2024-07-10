package com.tak.article.domain.article.repositoriy;

import com.tak.article.domain.article.entity.Post;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleRepositoryCustom {
    List<Post> findByTitle(String title);
    Page<Post> searchPage(String title, Pageable pageable);
}
