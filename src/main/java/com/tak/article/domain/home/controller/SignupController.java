package com.tak.article.domain.home.controller;

import com.tak.article.domain.home.exception.NotUniqueException;
import com.tak.article.domain.home.form.SignupForm;
import com.tak.article.domain.home.response.SignupCheckResponse;
import com.tak.article.domain.member.entity.Member;
import com.tak.article.domain.member.service.MemberService;
import jakarta.validation.Valid;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequiredArgsConstructor
public class SignupController {

    private final MemberService memberService;


    @GetMapping("/signup")
    public String signup(Model model, @ModelAttribute("signup") SignupForm signupForm) {
        model.addAttribute("signup", signupForm);
        return "login/sign-up";
    }

    @PostMapping("/signup")
    public String signUp(@Valid @ModelAttribute("signup") SignupForm form, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            bindingResult.addError(new ObjectError("signup", "올바른 접근으로 가입해주세요."));
            ControllerMethod.getErrorInfo(bindingResult);
            return "login/sign-up";
        }

        try {
            memberService.save(new Member(form));
            redirectAttributes.addFlashAttribute("success", true);
            return "redirect:/";

        } catch (NotUniqueException e) {
            bindingResult.reject("signup", "올바른 접근으로 가입해주세요.");
            return "login/sign-up";
        }
    }

    @GetMapping("/sign-up/check")
    public ResponseEntity<SignupCheckResponse> checkUsername(@RequestParam("key") String key,
                                                             @RequestParam("value") String value) {
        return checkUnique(key, value);
    }

    private ResponseEntity<SignupCheckResponse> checkUnique(String key, String value) {

        Optional<Member> member;
        if (key.equals("username")) {
            member = memberService.findByUsername(value);
            return member.isPresent() ? ResponseEntity.ok(new SignupCheckResponse(false, "이미 존재하는 아이디입니다"))
                    : ResponseEntity.ok(new SignupCheckResponse(true, "사용 가능한 아이디입니다."));
        } else {
            member = memberService.findByNickname(value);
            return member.isPresent() ? ResponseEntity.ok(new SignupCheckResponse(false, "이미 존재하는 닉네임입니다"))
                    : ResponseEntity.ok(new SignupCheckResponse(true, "사용 가능한 닉네임입니다."));
        }
    }
}
