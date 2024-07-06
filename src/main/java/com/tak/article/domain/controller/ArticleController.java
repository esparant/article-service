package com.tak.article.domain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArticleController {

    @GetMapping("/article")
    public String article() {
        return "article/article-home";
    }
}
