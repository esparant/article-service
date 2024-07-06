package com.tak.article.domain.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.tak.article.domain.entity.Member;
import com.tak.article.domain.exception.LoginException;
import com.tak.article.domain.exception.NotUniqueException;
import com.tak.article.domain.form.LoginForm;
import com.tak.article.domain.form.SignupForm;
import java.util.Optional;
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
        Optional<Member> resultA = memberService.findById(member.getId());
        assertThat(resultA.orElse(null)).isEqualTo(member);

        // username 로 조회
        Optional<Member> resultB = memberService.findByUsername(member.getUsername());
        assertThat(resultB.orElse(null)).isEqualTo(member);

        // nickname 로 조회
        Optional<Member> resultC = memberService.findByNickname(member.getNickname());
        assertThat(resultC.orElse(null)).isEqualTo(member);
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

        Optional<Member> result = memberService.login(new LoginForm("user1", "123"));

        assertThat(result.get()).isEqualTo(member);
    }

    @Test
    @DisplayName("비정상 로그인")
    void login_fail() {
        Member member = new Member(new SignupForm("user1", "123", "hello"));
        memberService.save(member);
        assertThatThrownBy(() -> memberService.login(new LoginForm("user2", "123")))
                .isInstanceOf(LoginException.class);

    }
}