package com.tak.article.domain.member.entity.dto;

import com.tak.article.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {


    public MemberDto(Member member) {
        this.id = member.getId();
        this.username = member.getUsername();
        this.nickname = member.getNickname();
    }

    private Long id;
    private String username;
    private String nickname;
}
