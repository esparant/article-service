package com.tak.article.domain.home.controller;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.support.RequestContextUtils;

@Slf4j
public abstract class ControllerMethod {

    static void getErrorInfo(BindingResult bindingResult) {
        log.info("errors: {}", bindingResult.getAllErrors());
    }

    static void checkSignupSuccess(Model model, HttpServletRequest request) {
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        if (inputFlashMap != null && inputFlashMap.containsKey("success")) {
            model.addAttribute("success", inputFlashMap.get("success"));
        }
    }
}
