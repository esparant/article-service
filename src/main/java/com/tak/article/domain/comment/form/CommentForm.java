package com.tak.article.domain.comment.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentForm {

    public CommentForm(String writer) {
        this.writer = writer;
    }

    private String writer;

    @NotBlank
    private String content;
}
