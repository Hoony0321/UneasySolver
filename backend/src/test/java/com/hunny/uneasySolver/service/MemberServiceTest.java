package com.hunny.uneasySolver.service;

import com.hunny.uneasySolver.domain.Member;
import com.hunny.uneasySolver.dto.MemberLoginRequest;
import com.hunny.uneasySolver.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired private MemberRepository memberRepository;
    @Autowired private MemberService memberService;

    @Test
    public void 회원가입() throws Exception{
        //given
        Member member = Member.createMember("testEmail@gmail.com", "password", "nickname", "region", null, null, null, null, null);

        //when
        Long saveId = memberRepository.save(member);

        //then
        assertThat(saveId).isEqualTo(member.getId());

    }

     @Test
     public void 회원조회_BY_ID() throws Exception{
         //given
         Member member = Member.createMember("testEmail@gmail.com", "password", "nickname", "region", null, null, null, null, null);

         //when
         Long saveId = memberRepository.save(member);
         Optional<Member> findOne = memberRepository.findById(saveId);

         //then
         assertThat(findOne.get().getEmail()).isEqualTo("testEmail@gmail.com");
    }

     @Test
     public void 전체회원조회() throws Exception{
         //given
         Member member1 = Member.createMember("testEmail1@gmail.com", "password", "nickname1", "region", null, null, null, null, null);
         memberRepository.save(member1);

         Member member2 = Member.createMember("testEmail2@gmail.com", "password", "nickname2", "region", null, null, null, null, null);
         memberRepository.save(member2);

         Member member3 = Member.createMember("testEmail3@gmail.com", "password", "nickname3", "region", null, null, null, null, null);
         memberRepository.save(member3);

         Member member4 = Member.createMember("testEmail4@gmail.com", "password", "nickname4", "region", null, null, null, null, null);
         memberRepository.save(member4);

         //when
         List<Member> members = memberRepository.findAll();

         //then
         assertThat(members.size()).isEqualTo(4);

    }

    @Test
    public void 회원_로그인_성공() throws Exception {
        //given
        MemberLoginRequest request = new MemberLoginRequest("root", "root");

        //when
        Member member = memberService.login(request);

        //then
        assertThat(member.getNickname()).isEqualTo("root");
    }

    @Test
    public void 회원_로그인_실패() throws Exception {
        //given
        MemberLoginRequest request = new MemberLoginRequest("root", "root123");

        //when
        Exception exception = assertThrows(Exception.class, () -> {
            memberService.login(request);
        });

        //then
        assertThat(exception.getMessage()).isEqualTo("로그인에 실패하셨습니다.");
    }
}