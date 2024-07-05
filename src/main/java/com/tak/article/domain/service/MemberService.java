package com.tak.article.domain.service;

import com.tak.article.domain.entity.Member;
import com.tak.article.domain.exception.LoginException;
import com.tak.article.domain.exception.signup.NotUniqueException;
import com.tak.article.domain.exception.signup.NotUniqueNicknameException;
import com.tak.article.domain.exception.signup.NotUniqueUsernameException;
import com.tak.article.domain.form.LoginForm;
import com.tak.article.domain.repository.MemberRepository;
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

        RuntimeException exception = CheckUnique(member);

        if (exception == null) {
            memberRepository.save(member);
            return;
        }

        throw exception;
    }

    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }

    // ToDo : 중복 버튼 검사용
    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    public Optional<Member> findByNickname(String nickname) {
        return memberRepository.findByNickname(nickname);
    }

    private NotUniqueException CheckUnique(Member member) {
        if (memberRepository.findByUsername(member.getUsername()).isPresent()) {
            return new NotUniqueUsernameException("아이디 중복");
        }
        if(memberRepository.findByNickname(member.getNickname()).isPresent()) {
            return new NotUniqueNicknameException("닉네임 중복");
        }
        return null;
    }

    public Optional<Member> login(LoginForm form) {
        Optional<Member> member = memberRepository.findByUsername(form.getUsername());

        if (member.isEmpty()) {
            throw new LoginException("아이디가 존재하지 않음");
        }
        if (!form.getPassword().equals(member.get().getPassword())) {
            throw new LoginException("비밀번호 불일치");
        }

        return member;
    }
}
