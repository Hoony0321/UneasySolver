package com.hunny.uneasySolver.dto;


import com.hunny.uneasySolver.domain.Member;
import com.hunny.uneasySolver.security.Authority;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class MemberDTO {

    private Long id;
    private String email;
    private String nickname;

    private Authority authority;


    public MemberDTO(){}

    public MemberDTO(Member member){
        this.id = member.getId();
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.authority = member.getAuthority();
    }
    public MemberDTO(Long id, String email, String nickname) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
    }

}
