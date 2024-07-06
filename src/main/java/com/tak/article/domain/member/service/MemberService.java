package com.tak.article.domain.member.service;

import com.tak.article.domain.member.entity.Member;
import com.tak.article.domain.home.exception.LoginException;
import com.tak.article.domain.home.exception.NotUniqueException;
import com.tak.article.domain.home.form.LoginForm;
import com.tak.article.domain.member.repository.MemberRepository;
import java.util.Optional;
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

    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }

    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    public Optional<Member> findByNickname(String nickname) {
        return memberRepository.findByNickname(nickname);
    }

    public Optional<Member> login(LoginForm form) {
        Optional<Member> member = memberRepository.findByUsername(form.getUsername());

        if (member.isEmpty() || !form.getPassword().equals(member.get().getPassword())) {
            throw new LoginException();
        }

        return member;
    }

    private void isUniqueFinalCheck(Member member) {
        if (memberRepository.findByUsername(member.getUsername()).isPresent() ||
                memberRepository.findByNickname(member.getNickname()).isPresent()) {
            throw new NotUniqueException("비정상적인 접근");
        }
    }
}
