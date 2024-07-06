package com.tak.article.domain.controller;

import com.tak.article.domain.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;

@Slf4j
@RequiredArgsConstructor
public abstract class ControllerMethod {

    private final MemberService memberService;

    static void getErrorInfo(BindingResult bindingResult) {
        log.info("errors: {}", bindingResult.getAllErrors());
    }


}
