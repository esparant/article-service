package com.tak.article.domain.article.form;

import lombok.Data;

@Data
public class SearchForm {

    private SearchOption searchOption = SearchOption.TITLE;
    private String searchValue = "";
}
