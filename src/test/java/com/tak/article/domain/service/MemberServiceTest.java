package com.tak.article.domain.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.tak.article.domain.home.exception.LoginException;
import com.tak.article.domain.home.exception.NotUniqueException;
import com.tak.article.domain.home.form.LoginForm;
import com.tak.article.domain.home.form.SignupForm;
import com.tak.article.domain.member.entity.Member;
import com.tak.article.domain.member.entity.dto.MemberDto;
import com.tak.article.domain.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Test
    @DisplayName("회원 등록 테스트")
    void saveMember() {
        // 1. 회원 정상 등록
        Member member = new Member(new SignupForm("user1", "123", "hello"));
        memberService.save(member);

        // id 로 조회
        Member resultA = memberService.findById(member.getId());
        assertThat(resultA).isEqualTo(member);

        // username 로 조회
        Member resultB = memberService.findByUsername(member.getUsername());
        assertThat(resultB).isEqualTo(member);

        // nickname 로 조회
        Member resultC = memberService.findByNickname(member.getNickname());
        assertThat(resultC).isEqualTo(member);
    }

    @Test
    @DisplayName("중복 회원 검사")
    void saveMember_fail() {
        // 1. 멤버 아이디(username)가 같을 때
        Member memberA = new Member(new SignupForm("user1", "123", "hello"));
        memberService.save(memberA);
        Member memberB = new Member(new SignupForm("user1", "123", "world"));
        assertThatThrownBy(() -> memberService.save(memberB)).isInstanceOf(NotUniqueException.class);
        assertThat(memberB.getId()).isNull();

        // 2. 멤버 닉네임이 같을 때
        Member memberC = new Member(new SignupForm("user2", "123", "hello"));
        assertThatThrownBy(() -> memberService.save(memberC)).isInstanceOf(NotUniqueException.class);
        assertThat(memberC.getId()).isNull();
    }

    @Test
    @DisplayName("정상 로그인")
    void login() {
        Member member = new Member(new SignupForm("user1", "123", "hello"));
        memberService.save(member);

        Member result = memberService.login(new LoginForm("user1", "123"));

        assertThat(result).isEqualTo(member);
    }

    @Test
    @DisplayName("비정상 로그인")
    void login_fail() {
        Member member = new Member(new SignupForm("user1", "123", "hello"));
        memberService.save(member);
        assertThatThrownBy(() -> memberService.login(new LoginForm("user2", "123")))
                .isInstanceOf(LoginException.class);

    }

    @Test
    @DisplayName("비밀번호 변경")
    void changePassword() {
        Member member = new Member(new SignupForm("user1", "123", "hello"));
        memberService.save(member);
        memberService.changePassword(new MemberDto(member.getId(), member.getUsername(), member.getNickname()), "123456");

        assertThat(memberService.findById(member.getId()).getPassword()).isEqualTo("123456");
    }
}