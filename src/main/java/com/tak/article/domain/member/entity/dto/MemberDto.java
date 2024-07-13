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
        id = member.getId();
        username = member.getUsername();
        nickname = member.getNickname();
//        createdDate = member.getCreateDate();
//        updatedDate = member.getUpdateDate();
    }

    private Long id;
    private String username;
    private String nickname;
//    private LocalDateTime createdDate;
//    private LocalDateTime updatedDate;
}
