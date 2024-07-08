package com.tak.article.domain.home.controller;

import static com.tak.article.domain.home.controller.ControllerMethod.checkSignupSuccess;

import com.tak.article.domain.home.form.LoginForm;
import com.tak.article.domain.member.entity.dto.MemberDto;
import com.tak.article.domain.member.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String home(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) MemberDto memberDto,
                       @ModelAttribute("login") LoginForm form, Model model,
                       HttpServletRequest request) {

        checkSignupSuccess(model, request);

        if (memberDto == null) {
            return "home";
        }

        model.addAttribute("member", memberDto);
        return "user/user-home";
    }


    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute(SessionConst.LOGIN_MEMBER);

        return "redirect:/";
    }
}
