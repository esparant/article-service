package com.tak.article.domain.member.service;

import com.tak.article.domain.home.exception.LoginException;
import com.tak.article.domain.home.exception.NotUniqueException;
import com.tak.article.domain.home.form.LoginForm;
import com.tak.article.domain.member.entity.Member;
import com.tak.article.domain.member.entity.dto.MemberDto;
import com.tak.article.domain.member.exception.NotEqualMemberException;
import com.tak.article.domain.member.exception.NotExistMemberException;
import com.tak.article.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void save(Member member) {

        isUniqueFinalCheck(member);

        memberRepository.save(member);

    }

    public Member findById(Long id) {
        return memberRepository.findById(id).orElseThrow(NotExistMemberException::new);
    }

    public Member findByUsername(String username) {
        return memberRepository.findByUsername(username).orElseThrow(NotExistMemberException::new);
    }

    public Member findByNickname(String nickname) {
        return memberRepository.findByNickname(nickname).orElseThrow(NotExistMemberException::new);
    }

    public Member login(LoginForm form) {

        Member member = memberRepository.findByUsername(form.getUsername()).orElseThrow(LoginException::new);

        if (!form.getPassword().equals(member.getPassword())) {
            throw new LoginException();
        }

        return member;
    }

    @Transactional
    public void changePassword(Long memberId, MemberDto loginMember, String updatePassword) {
        Member member = memberRepository.findById(memberId).orElseThrow(NotExistMemberException::new);
        if (!member.getUsername().equals(loginMember.getUsername())) {
            throw new NotEqualMemberException();
        }

        member.changePassword(updatePassword);
    }

    private void isUniqueFinalCheck(Member member) {
        if (memberRepository.findByUsername(member.getUsername()).isPresent() ||
                memberRepository.findByNickname(member.getNickname()).isPresent()) {
            throw new NotUniqueException("비정상적인 접근");
        }
    }
}
