package com.tak.article.domain.controller;

import static com.tak.article.domain.controller.ControllerMethod.getErrorInfo;

import com.tak.article.domain.entity.Member;
import com.tak.article.domain.exception.signup.NotUniqueException;
import com.tak.article.domain.exception.signup.NotUniqueNicknameException;
import com.tak.article.domain.exception.signup.NotUniqueUsernameException;
import com.tak.article.domain.form.SignupForm;
import com.tak.article.domain.response.MemberCheckResponse;
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


// TODO REST CONTROLLER 로 리펙토링
@Controller
@RequiredArgsConstructor
public class SignupController {

    private final MemberService memberService;


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

    @GetMapping("/sign-up/check")
    public ResponseEntity<MemberCheckResponse> checkUsername(@RequestParam("key") String key, @RequestParam("value") String value) {
        
        Optional<Member> member;
        
        if (key.equals("username")) {
            member = memberService.findByUsername("username");
            return member.isPresent() ? ResponseEntity.ok(new MemberCheckResponse(false, "이미 존재하는 아이디입니다"))
                    : ResponseEntity.ok(new MemberCheckResponse(true, "사용 가능한 아이디입니다."));
        } else {
            member = memberService.findByNickname("nickname");
            return member.isPresent() ? ResponseEntity.ok(new MemberCheckResponse(false, "이미 존재하는 닉네임입니다"))
                    : ResponseEntity.ok(new MemberCheckResponse(true, "사용 가능한 닉네임입니다."));
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

}
