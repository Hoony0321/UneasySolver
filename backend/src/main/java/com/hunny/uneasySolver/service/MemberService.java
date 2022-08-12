package com.hunny.uneasySolver.service;

import com.hunny.uneasySolver.domain.Member;
import com.hunny.uneasySolver.exception.LoginException;
import com.hunny.uneasySolver.form.MemberLoginForm;
import com.hunny.uneasySolver.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
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
}
