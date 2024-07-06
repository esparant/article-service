package com.tak.article.domain.home.init;

import com.tak.article.domain.member.entity.Member;
import com.tak.article.domain.home.form.SignupForm;
import com.tak.article.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
@Profile("dev")
@RequiredArgsConstructor
public class InitClass {

    private final MemberRepository repository;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        repository.save(new Member(new SignupForm("test123", "123", "test")));
    }
}

