package com.tak.article.domain.controller;

import com.tak.article.domain.entity.Member;
import com.tak.article.domain.form.LoginForm;
import com.tak.article.domain.form.SignupForm;
import com.tak.article.domain.exception.LoginException;
import com.tak.article.domain.exception.signup.NotUniqueException;
import com.tak.article.domain.exception.signup.NotUniqueNicknameException;
import com.tak.article.domain.exception.signup.NotUniqueUsernameException;
import com.tak.article.domain.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    // ToDo 메시지 언어 설정 해보기

    private final MemberService memberService;

    @GetMapping
    public String Home(Model model) {
        model.addAttribute("login", new LoginForm());

        // TODO : 세션 여부에 따라 다른 view 만들기
        return "login/login-home";
    }

    @PostMapping
    public String Login(@Valid @ModelAttribute("login") LoginForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            getErrorInfo(bindingResult);
            return "login/login-home";
        }

        try {
            memberService.login(form);
            return "redirect:/";
        } catch (LoginException e) {
            bindingResult.addError(new ObjectError("member", "아이디가 존재하지 않거나 비밀번호가 맞지 않습니다."));
            getErrorInfo(bindingResult);
            return "login/login-home";
        }
    }

    @GetMapping("/signup")
    public String SignUp(Model model) {
        model.addAttribute("signup", new SignupForm());
        return "login/sign-up";
    }

    @PostMapping("/signup")
    public String SignUp(@Valid @ModelAttribute("signup") SignupForm form, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            getErrorInfo(bindingResult);
            return "login/sign-up";
        }

        // ToDo 중복 검사 버튼으로 대체하기 (아이디, 닉네임)
        try {
            memberService.save(new Member(form));
            return "redirect:/signup?success";
        } catch (NotUniqueException e) {
            bindingResult.addError(new ObjectError("member", checkNotUniqueException(e)));
            return "login/sign-up";
        }
    }

    private String checkNotUniqueException(NotUniqueException e) {
        if (e instanceof NotUniqueUsernameException) {
            return "중복된 아이디 입니다.";
        }
        if (e instanceof NotUniqueNicknameException) {
            return "중복된 닉네임 입니다.";
        }
        return null;
    }

    private static void getErrorInfo(BindingResult bindingResult) {
        log.info("errors: {}", bindingResult.getAllErrors());
    }
}
