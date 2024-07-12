package com.tak.article.domain.home.controller;

import com.tak.article.domain.member.entity.Member;
import com.tak.article.domain.member.entity.dto.MemberDto;
import com.tak.article.domain.member.exception.NotExistMemberException;
import com.tak.article.domain.member.service.MemberService;
import com.tak.article.domain.session.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class MyPageController {

    private final MemberService memberService;


    @GetMapping("/my-page")
    public String myPage(@SessionAttribute(required = false, value = SessionConst.LOGIN_MEMBER) MemberDto loginMember,
                         Model model, RedirectAttributes redirectAttributes) {

        try {
            Member member = memberService.findById(loginMember.getId());
            model.addAttribute("member", member);
            return "user/my-page";
        } catch (NotExistMemberException e) {
            redirectAttributes.addFlashAttribute("enterMyPageFail", true);
            return "redirect:/";
        }
    }
}
