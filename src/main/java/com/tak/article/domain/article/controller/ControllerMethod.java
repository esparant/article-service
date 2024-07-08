package com.tak.article.domain.article.controller;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import org.springframework.ui.Model;
import org.springframework.web.servlet.support.RequestContextUtils;

public class ControllerMethod {

    final private static String NOT_EXIST_POST = "notExistPost";

    static void checkNotExistPost(Model model, HttpServletRequest request) {
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        if (inputFlashMap != null && inputFlashMap.containsKey(NOT_EXIST_POST)) {
            model.addAttribute(NOT_EXIST_POST, inputFlashMap.get(NOT_EXIST_POST));
        }
    }
}
