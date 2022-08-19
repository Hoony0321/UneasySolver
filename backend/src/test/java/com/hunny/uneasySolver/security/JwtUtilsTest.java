package com.hunny.uneasySolver.security;

import com.hunny.uneasySolver.domain.Member;
import com.hunny.uneasySolver.dto.MemberDTO;
import com.hunny.uneasySolver.dto.MemberLoginRequest;
import com.hunny.uneasySolver.service.MemberService;
import io.jsonwebtoken.Claims;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class JwtUtilsTest {

    @Autowired private MemberService memberService;
    @Autowired private JwtUtils jwtUtils;

    @Test
    public void 토큰_생성() throws Exception {
        //given
        MemberLoginRequest request = new MemberLoginRequest("root", "root");
        Member member = memberService.login(request);

        //when
        String token = jwtUtils.generateToken(new MemberDTO(member)); //토큰 생성
        Claims content = jwtUtils.getContentFromJWT(token);

        //then
        assertThat(content.get("id")).isEqualTo(2);
        assertThat(content.get("nickname")).isEqualTo("root");
        assertThat(content.get("email")).isEqualTo("root");

    }

}