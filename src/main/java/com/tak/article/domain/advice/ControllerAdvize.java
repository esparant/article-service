package com.tak.article.domain.advice;

import com.tak.article.domain.article.exception.NotExistPostException;
import com.tak.article.domain.member.exception.NotEqualMemberException;
import com.tak.article.domain.member.exception.NotExistMemberException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@ControllerAdvice
public class ControllerAdvize {

    @ExceptionHandler(NotExistPostException.class)
    public RedirectView notExistPostException(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("notExistPost", true);

        return new RedirectView("/article");
    }

    @ExceptionHandler(NotEqualMemberException.class)
    public RedirectView notEqualMemberException(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("notEqualMember", true);
        return new RedirectView("/");
    }

    @ExceptionHandler(NotExistMemberException.class)
    public RedirectView notExistMemberException(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("notExistMember", true);
        return new RedirectView("/");
    }

}
