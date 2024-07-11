package com.tak.article.domain.advice;

import com.tak.article.domain.article.exception.NotExistPostException;
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

}
