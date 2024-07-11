package com.tak.article.domain.comment.controller;


import com.tak.article.domain.comment.entity.Comment;
import com.tak.article.domain.comment.exception.NotExistCommentException;
import com.tak.article.domain.comment.form.CommentForm;
import com.tak.article.domain.comment.service.CommentService;
import com.tak.article.domain.func.ControllerMethod;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
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
                               @PathVariable("id") Long postId) {

        if (bindingResult.hasErrors()) {
            ControllerMethod.getErrorInfo(bindingResult);
            return "redirect:/post/" + postId;
        }

        log.info("comment form: {}", form);

        commentService.saveComment(postId, new Comment(form));

        redirectAttributes.addFlashAttribute("addCommentSuccess", true);

        return "redirect:/post/" + postId;
    }

    @PostMapping("/post/{postId}/modify-comment/{commId}")
    public ResponseEntity<Comment> updateComment(@PathVariable("commId") Long commId,
                                                 @ModelAttribute CommentForm form) {

        log.info("comment form: {}", form);

        try {
            commentService.modifyComment(commId, form);
            return ResponseEntity.ok(new Comment(form));
        } catch (NotExistCommentException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/post/{postId}/delete-comment/{commId}")
    public String deleteComment(@PathVariable("postId") Long postId,
                                @PathVariable("commId") Long commId,
                                RedirectAttributes redirectAttributes) {

        try {
            commentService.deleteComment(postId, commId);
            redirectAttributes.addFlashAttribute("deleteCommentSuccess", true);
        } catch (NotExistCommentException e) {
            redirectAttributes.addFlashAttribute("notExistComment", true);
        }

        return "redirect:/post/" + postId;

    }
}
