package com.tak.article.domain.comment.controller;


import com.tak.article.domain.comment.entity.Comment;
import com.tak.article.domain.comment.form.CommentForm;
import com.tak.article.domain.comment.service.CommentService;
import com.tak.article.domain.func.ControllerMethod;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/post/{id}/add-comment")
    public String writeComment(@Valid @ModelAttribute("comment") CommentForm form,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               @PathVariable("id") Long id) {

        if (bindingResult.hasErrors()) {
            ControllerMethod.getErrorInfo(bindingResult);
            return "redirect:/post/" + id;
        }

        log.info("comment form: {}", form);

        commentService.saveComment(id, new Comment(form));

        redirectAttributes.addFlashAttribute("addCommentSuccess", true);

        return "redirect:/post/" + id;
    }

}
