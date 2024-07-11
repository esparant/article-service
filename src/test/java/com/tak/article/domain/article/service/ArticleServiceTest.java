package com.tak.article.domain.article.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.tak.article.domain.article.entity.Post;
import com.tak.article.domain.article.form.PostForm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class ArticleServiceTest {

    @Autowired
    ArticleService articleService;


    @Test
    @DisplayName("게시글 작성")
    void savePost() {

        Post post = new Post("hello", "hello", "me");
        articleService.savePost(post);
        Post result = articleService.getPost(post.getId());
        assertThat(result).isEqualTo(post);

        assertThat(articleService.getPostList()).hasSize(1);

    }

    @Test
    @DisplayName("게시글 수정")
    void modifyPost() {
        Post post = new Post("hello", "hello", "me");
        articleService.savePost(post);

        post.modifyPost(new PostForm(new Post("nono", "oh my god", "me")));

        Post result = articleService.getPost(post.getId());

        assertThat(result.getTitle()).isEqualTo("nono");
        assertThat(result.getContent()).isEqualTo("oh my god");
    }

    @Test
    @DisplayName("게시글 삭제")
    void deletePost() {
        Post post = new Post("hello", "hello", "me");
        articleService.savePost(post);
        articleService.deletePost(post.getId());
        assertThat(articleService.getPostList()).isEmpty();
    }

    @Test
    @DisplayName("검색 시스템")
    void searchPost() {
        articleService.savePost(new Post("hello", "hello", "me"));
        articleService.savePost(new Post("hello2", "hello2", "me2"));
        articleService.savePost(new Post("hello3", "hello3", "me3"));

        Page<Post> resultA = articleService.searchPost("hello2", PageRequest.of(0, 10));
        assertThat(resultA.getTotalElements()).isEqualTo(1);
        assertThat(resultA.getTotalPages()).isEqualTo(1);

        Page<Post> resultB = articleService.searchPost("hello", PageRequest.of(0, 10));
        assertThat(resultB.getTotalElements()).isEqualTo(3);

    }
}