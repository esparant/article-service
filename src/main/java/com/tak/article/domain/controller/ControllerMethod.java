package com.tak.article.domain.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;

@Slf4j
public abstract class ControllerMethod {
    static void getErrorInfo(BindingResult bindingResult) {
        log.info("errors: {}", bindingResult.getAllErrors());
    }
}
