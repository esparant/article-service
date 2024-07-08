package com.tak.article.domain.article.init;

import com.tak.article.domain.article.entity.Post;
import com.tak.article.domain.article.repositoriy.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
@RequiredArgsConstructor
public class ArticleInitClass {

    private final ArticleRepository articleRepository;

    @EventListener(ApplicationReadyEvent.class)
    private void init() {
        articleRepository.save(new Post("test1", "test1", "test"));
        articleRepository.save(new Post("test1", "test1", "test"));
        articleRepository.save(new Post("test1", "test1", "test"));
        articleRepository.save(new Post("test1", "test1", "test"));

    }
}
