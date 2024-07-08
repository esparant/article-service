package com.tak.article.domain.article.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.tak.article.domain.article.entity.Post;
import com.tak.article.domain.article.form.PostForm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class ArticleServiceTest {

    @Autowired
    ArticleService articleService;


    @Test
    @DisplayName("게시글 작성")
    void writePost() {

        Post post = new Post("hello", "hello", "me");
        articleService.writePost(post);
        Post result = articleService.getPost(post.getId());
        assertThat(result).isEqualTo(post);

        assertThat(articleService.getPostList()).hasSize(1);

    }

    @Test
    @DisplayName("게시글 수정")
    void modifyPost() {
        Post post = new Post("hello", "hello", "me");
        articleService.writePost(post);
        post.modifyPost(new PostForm("nono", "oh my god"));

        Post result = articleService.getPost(post.getId());

        assertThat(result.getTitle()).isEqualTo("nono");
        assertThat(result.getContent()).isEqualTo("oh my god");

    }

}