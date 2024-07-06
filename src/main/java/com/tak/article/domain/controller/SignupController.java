package com.tak.article.domain.controller;

import static com.tak.article.domain.controller.ControllerMethod.getErrorInfo;

import com.tak.article.domain.entity.Member;
import com.tak.article.domain.exception.NotUniqueException;
import com.tak.article.domain.form.SignupForm;
import com.tak.article.domain.response.SignupCheckResponse;
import com.tak.article.domain.service.MemberService;
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
    public String signUp(@Valid @ModelAttribute("signup") SignupForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.addError(new ObjectError("signup", "올바른 접근으로 가입해주세요."));
            getErrorInfo(bindingResult);
            return "login/sign-up";
        }

        try {
            memberService.save(new Member(form));
            return "redirect:/signup?success";

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
