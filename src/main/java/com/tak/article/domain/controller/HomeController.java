package com.tak.article.domain.controller;

import static com.tak.article.domain.controller.ControllerMethod.getErrorInfo;

import com.tak.article.domain.entity.Member;
import com.tak.article.domain.entity.dto.MemberDto;
import com.tak.article.domain.exception.LoginException;
import com.tak.article.domain.form.LoginForm;
import com.tak.article.domain.service.MemberService;
import com.tak.article.domain.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {


    private final MemberService memberService;

    @GetMapping
    public String Home(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member
            , @ModelAttribute("login") LoginForm form, Model model) {

        if (member == null) {
            return "login/login-home";
        }

        model.addAttribute("member", new MemberDto(member));
        return "user/user-home";
    }

    @PostMapping
    public String Login(@Valid @ModelAttribute("login") LoginForm form, BindingResult bindingResult,
                        HttpServletRequest request, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            getErrorInfo(bindingResult);
            return "/login/login-home";
        }

        try {
            Optional<Member> loginMember = memberService.login(form);
            request.getSession().setAttribute(SessionConst.LOGIN_MEMBER, loginMember.orElse(null));
            return "redirect:/";
        } catch (LoginException e) {
            bindingResult.addError(new ObjectError("global", "아이디가 존재하지 않거나 비밀번호가 일치하지 않습니다."));
            getErrorInfo(bindingResult);
            return "/login/login-home";
        }
    }

    @PostMapping("/logout")
    public String Logout(HttpServletRequest request) {
        request.getSession().removeAttribute(SessionConst.LOGIN_MEMBER);

        return "redirect:/";
    }
}
