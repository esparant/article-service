package com.tak.article.domain.article.controller;

import com.tak.article.domain.article.entity.Post;
import com.tak.article.domain.article.exception.NotExistPostException;
import com.tak.article.domain.article.form.PostForm;
import com.tak.article.domain.article.service.ArticleService;
import com.tak.article.domain.member.entity.dto.MemberDto;
import com.tak.article.domain.member.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;


    @GetMapping("/article")
    public String article(Model model, HttpServletRequest request) {

        ControllerMethod.checkNotExistPost(model, request);

        model.addAttribute("posts", articleService.getPostList());

        return "article/article-home";
    }

    @GetMapping("/post")
    public String getForm(@ModelAttribute("form") PostForm form) {
        return "article/post-form";
    }

    @PostMapping("/post")
    public String post(@ModelAttribute("form") PostForm form,
                       @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) MemberDto memberDto) {

        articleService.writePost(new Post(form, memberDto));

        return "redirect:/article";
    }

    @GetMapping("/post/{id}")
    public String getPost(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes,
                          @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) MemberDto memberDto) {

        try {
            model.addAttribute("post", articleService.getPost(id));
            model.addAttribute("member", memberDto);
            return "article/view";
        } catch (NotExistPostException e) {
            redirectAttributes.addFlashAttribute("notExistPost", true);
            return "redirect:/article";
        }
    }

    @GetMapping("/modify-post/{id}")
    public String modifyPost(@PathVariable("id") Long id, Model model) {
        model.addAttribute("post", articleService.getPost(id));
        return "article/modify-post-form";
    }
}
