package com.tak.article.domain.comment.service;

import com.tak.article.domain.article.entity.Post;
import com.tak.article.domain.article.service.ArticleService;
import com.tak.article.domain.comment.entity.Comment;
import com.tak.article.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleService articleService;

    @Transactional
    public void saveComment(Long postId, Comment comment) {
        Post post = articleService.getPost(postId);
        post.addComment(comment);
        commentRepository.save(comment);
    }
}
