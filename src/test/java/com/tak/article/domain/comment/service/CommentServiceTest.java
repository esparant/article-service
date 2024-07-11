package com.tak.article.domain.comment.service;

import com.tak.article.domain.article.entity.Post;
import com.tak.article.domain.article.service.ArticleService;
import com.tak.article.domain.comment.entity.Comment;
import com.tak.article.domain.comment.exception.NotExistCommentException;
import com.tak.article.domain.comment.form.CommentForm;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class CommentServiceTest {
    @Autowired
    CommentService commentService;
    @Autowired
    ArticleService articleService;

    @BeforeEach
    void setUp() {
        articleService.writePost(new Post("hello1", "hello1", "tester"));
    }


    @Test
    @DisplayName("댓글 작성")
    void writeComment() {
        Comment comment = new Comment(new CommentForm("hello", "123"));
        commentService.saveComment(1L, comment);

        Post post = articleService.getPost(1L);

        Assertions.assertThat(post.getComments().size()).isEqualTo(1);
        Assertions.assertThat(post.getComments().getFirst()).isEqualTo(comment);
    }

    @Test
    @DisplayName("댓글 수정")
    void modifyComment() {
        Comment comment = new Comment(new CommentForm("hello", "123"));
        commentService.saveComment(1L, comment);

        commentService.modifyComment(comment.getId(), new CommentForm("hello", "456"));
        Post post = articleService.getPost(1L);

        Assertions.assertThat(post.getComments().getFirst().getContent()).isEqualTo("456");
    }

    @Test
    @DisplayName("댓글 삭제")
    void deleteComment() {
        Comment comment = new Comment(new CommentForm("hello", "123"));
        commentService.saveComment(1L, comment);
        commentService.deleteComment(1L, comment.getId());
        Post post = articleService.getPost(1L);

        Assertions.assertThatThrownBy(
                () -> commentService.findComment(comment.getId())
        ).isInstanceOf(NotExistCommentException.class);

        Assertions.assertThat(post.getComments().size()).isEqualTo(0);
    }
}