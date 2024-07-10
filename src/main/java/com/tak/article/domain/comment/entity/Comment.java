package com.tak.article.domain.comment.entity;

import com.tak.article.domain.article.entity.Post;
import com.tak.article.domain.comment.form.CommentForm;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    public Comment(CommentForm form) {
        this.content = form.getContent();
        this.writer = form.getWriter();
    }

    @GeneratedValue
    @Id
    private Long id;
    private String content;
    private String writer;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

}
