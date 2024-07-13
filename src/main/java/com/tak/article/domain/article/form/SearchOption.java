package com.tak.article.domain.article.form;

import lombok.Getter;

@Getter
public enum SearchOption {
    TITLE("제목"), WRITER("작성자");

    final String value;

    SearchOption(String value) {
        this.value = value;
    }
}
