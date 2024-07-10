package com.tak.article.domain.comment.repository;

import com.tak.article.domain.comment.entity.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
