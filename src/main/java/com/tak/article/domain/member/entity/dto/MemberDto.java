package com.tak.article.domain.member.entity.dto;

import com.tak.article.domain.member.entity.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberDto {

    public MemberDto(Member member) {
        this.username = member.getUsername();
        this.nickname = member.getNickname();
    }

    private String username;
    private String nickname;
}
