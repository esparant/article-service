package com.tak.article.domain.comment.service;

import com.tak.article.domain.article.entity.Post;
import com.tak.article.domain.article.exception.NotExistPostException;
import com.tak.article.domain.article.repositoriy.ArticleRepository;
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
    private final ArticleRepository articleRepository;

    public void saveComment(Long postId, Comment comment) {
        Post post = articleRepository.findById(postId).orElseThrow(NotExistPostException::new);
        comment.addComment(post);
        commentRepository.save(comment);
    }

    public void modifyComment(Long commentId, CommentForm form) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(NotExistCommentException::new);

        comment.modifyComment(form);
    }

    public void deleteComment(Long postId, Long commentId) {
        Post post = articleRepository.findById(postId).orElseThrow(NotExistPostException::new);
        Comment comment = commentRepository.findById(commentId).orElseThrow(NotExistCommentException::new);

        post.removeComment(comment);
        commentRepository.delete(comment);
    }

    @Transactional(readOnly = true)
    public Comment findComment(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(NotExistCommentException::new);
    }
}
