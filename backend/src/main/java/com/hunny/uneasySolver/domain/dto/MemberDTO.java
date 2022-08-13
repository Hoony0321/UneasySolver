package com.hunny.uneasySolver.domain.dto;


import com.hunny.uneasySolver.domain.Member;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberDTO {

    private Long id;
    private String email;
    private String nickname;

    public static MemberDTO createMemberCookie(Member member){
        MemberDTO cookie = new MemberDTO();
        cookie.setId(member.getId());
        cookie.setEmail(member.getEmail());
        cookie.setNickname(member.getNickname());

        return cookie;
    }

}
