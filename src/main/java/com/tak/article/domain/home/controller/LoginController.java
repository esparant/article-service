package com.tak.article.domain.home.controller;

import static com.tak.article.domain.func.ControllerMethod.getErrorInfo;

import com.tak.article.domain.home.exception.LoginException;
import com.tak.article.domain.home.form.LoginForm;
import com.tak.article.domain.member.entity.Member;
import com.tak.article.domain.member.entity.dto.MemberDto;
import com.tak.article.domain.member.service.MemberService;
import com.tak.article.domain.member.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("login") LoginForm form) {

        return "login/login-home";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("login") LoginForm form, BindingResult bindingResult,
                        @RequestParam(value = "redirectURL", defaultValue = "/") String redirectUrl,
                        HttpServletRequest request) {

        log.info("redirect URL = {}", redirectUrl);

        if (bindingResult.hasErrors()) {
            getErrorInfo(bindingResult);
            return "/login/login-home";
        }

        try {
            Optional<Member> loginMember = memberService.login(form);

            Member member = loginMember.orElseThrow(LoginException::new);
            request.getSession().setAttribute(SessionConst.LOGIN_MEMBER, new MemberDto(member));
            return "redirect:" + redirectUrl;
        } catch (LoginException e) {
            bindingResult.reject("loginFail", "아이디가 존재하지 않거나 비밀번호가 일치하지 않습니다.");
            getErrorInfo(bindingResult);
            return "/login/login-home";
        }
    }

}
