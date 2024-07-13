package com.tak.article.domain.article.form;

import com.tak.article.domain.article.entity.Post;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostForm {

    public PostForm(Post post) {
        id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();

    }

    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String content;
}
