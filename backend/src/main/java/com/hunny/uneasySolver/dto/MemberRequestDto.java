package com.hunny.uneasySolver.dto;

import com.hunny.uneasySolver.domain.Member;
import com.hunny.uneasySolver.security.Authority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDto {

    private String email;
    private String password;

    public Member toMember(PasswordEncoder passwordEncoder){
        return Member.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .nickname("test1234")
                .address("test")
                .authority(Authority.ROLE_USER).build();
    }

}
