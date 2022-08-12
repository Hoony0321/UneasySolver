package com.hunny.uneasySolver.session;


import com.hunny.uneasySolver.domain.Member;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberInfo_simple {

    private Long id;
    private String email;
    private String nickname;

    public static MemberInfo_simple createMemberCookie(Member member){
        MemberInfo_simple cookie = new MemberInfo_simple();
        cookie.setId(member.getId());
        cookie.setEmail(member.getEmail());
        cookie.setNickname(member.getNickname());

        return cookie;
    }

}
