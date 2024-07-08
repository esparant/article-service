package com.tak.article.domain.article.form;

import com.tak.article.domain.article.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostForm {

    public PostForm(Post post) {
        this.title = post.getTitle();
        this.content = post.getContent();

    }

    private String title;
    private String content;
}
