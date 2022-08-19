package com.hunny.uneasySolver.service;

import com.hunny.uneasySolver.security.JwtUtils;
import com.hunny.uneasySolver.domain.Member;
import com.hunny.uneasySolver.dto.MemberRegisterRequest;
import com.hunny.uneasySolver.exception.LoginException;
import com.hunny.uneasySolver.form.MemberLoginForm;
import com.hunny.uneasySolver.dto.MemberLoginRequest;
import com.hunny.uneasySolver.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository, JwtUtils jwtUtils) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member member){
        // TODO 이메일 중복 검사
        // TODO 닉네임 중복 검사
        return memberRepository.save(member);
    }

    public Optional<Member> findById(Long id){
        return memberRepository.findById(id);
    }

    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    public Member login(MemberLoginForm form) {
        Optional<Member> findMember = memberRepository.findByEmail(form.getEmail());
        if(findMember.isEmpty()){throw new LoginException("로그인에 실패하셨습니다.");}

        return findMember.get();
    }

    public Member login(MemberLoginRequest request){
        Optional<Member> result = memberRepository.findByEmail(request.getEmail());
        if(result.isEmpty()){throw new LoginException("존재하지 않는 회원 이메일입니다.");}

        Member member = result.get();
        if(!member.comparePassword(request.getPassword())){
            throw new LoginException("로그인에 실패하셨습니다.");
        }


        return member;
    }

    public void join(MemberRegisterRequest request) {
        Member member = Member.registerMember(request);
        memberRepository.save(member);
    }

    public boolean checkEmailDuplicated(String email){
        boolean result = false;
        for (Member member : memberRepository.findAll()) {
            if(member.getEmail().equals(email)){
                result = true;
                break;
            }
        }
        return result;
    }

    public boolean checkNickNameDuplicated(String nickname){
        boolean result = false;
        for (Member member : memberRepository.findAll()) {
            if(member.getNickname().equals(nickname)){
                result = true;
                break;
            }
        }
        return result;
    }
}
