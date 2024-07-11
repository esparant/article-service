package com.tak.article.domain.comment.service;

import com.tak.article.domain.article.entity.Post;
import com.tak.article.domain.article.service.ArticleService;
import com.tak.article.domain.comment.entity.Comment;
import com.tak.article.domain.comment.exception.NotExistCommentException;
import com.tak.article.domain.comment.form.CommentForm;
import com.tak.article.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleService articleService;

    public void saveComment(Long postId, Comment comment) {
        Post post = articleService.getPost(postId);
        comment.addComment(post);
        commentRepository.save(comment);
    }

    public void modifyComment(Long commentId, CommentForm form) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(NotExistCommentException::new);

        comment.modifyComment(form);
    }

    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(NotExistCommentException::new);
        commentRepository.delete(comment);
    }
}
