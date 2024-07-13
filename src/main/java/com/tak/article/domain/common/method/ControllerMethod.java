package com.tak.article.domain.common.method;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;

@Slf4j
public abstract class ControllerMethod {

    public static void getErrorInfo(BindingResult bindingResult) {
        log.info("errors: {}", bindingResult.getAllErrors());
    }

}
