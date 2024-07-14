package com.tak.article.domain.article.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchForm {

    private SearchOption searchOption = SearchOption.TITLE;
    private String searchValue = "";
}
