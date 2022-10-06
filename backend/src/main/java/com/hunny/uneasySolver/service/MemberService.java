package com.hunny.uneasySolver.service;

import com.hunny.uneasySolver.domain.Member;
import com.hunny.uneasySolver.dto.MemberRegisterRequest;
import com.hunny.uneasySolver.exception.LoginException;
import com.hunny.uneasySolver.dto.MemberLoginRequest;
import com.hunny.uneasySolver.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long join(Member member){
        // TODO 이메일 중복 검사
        // TODO 닉네임 중복 검사
        return memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public Optional<Member> findById(Long id){
        return memberRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Member> findAll(){
        return memberRepository.findAll();
    }


    @Transactional(readOnly = true)
    public Member login(MemberLoginRequest request){
        Optional<Member> result = memberRepository.findByEmail(request.getEmail());
        if(result.isEmpty()){throw new LoginException("존재하지 않는 회원 이메일입니다.");}

        Member member = result.get();
        if(!passwordEncoder.matches(request.getPassword(), member.getPassword())){
            throw new LoginException("로그인에 실패하셨습니다.");
        }


        return member;
    }


    @Transactional
    public void join(MemberRegisterRequest request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        Member member = Member.registerMember(request);
        memberRepository.save(member);
    }

    @Transactional(readOnly = true)
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

    @Transactional(readOnly = true)
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
