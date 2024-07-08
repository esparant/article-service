package com.tak.article.domain.article.entity;

import com.tak.article.domain.article.form.PostForm;
import com.tak.article.domain.member.entity.dto.MemberDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post {

    public Post(PostForm form, MemberDto dto) {
        this.title = form.getTitle();
        this.content = form.getContent();
        writer =  dto.getNickname();
        views = 0L;
    }

    public Post(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        views = 0L;
    }

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Lob
    private String content;

    private String writer;

    private Long views;

    public void incrementViews() {
        views ++;
    }

    public void modifyPost(PostForm form) {
        title = form.getTitle();
        content = form.getContent();
    }
}
